/**
 * Version 3.1
 * 3.1 CHANGES 
 * 	- Add thread view, and datasourses
 * 
 * 3 Adapt for up 3
 * 2.4 CHANGES
 *  - add thread view
 * 2.3.1 CHANGES
 *  - modify MessageFormat with jdk1.5
 * 2.3 CHANGES 
 *  - read max user to show in web.xml
 *  - count only connected user to limit  max user to show
 * 2.2 CHANGES
 *  - add xmlfull
 *  - add maxDumpUser
 *  
 *  Exemple : 
 *  /EsupMonitor : Display memory information and user connected (line mode)
 *  /EsupMonitor?header=1 : Display headers for  memory information and user connected (line mode)
 *  /EsupMonitor?xml=1 : Display memory information and user connected (xml mode)
 *  /EsupMonitor?xml=full : Display memory information and user connected (xml mode) full  for list login use connected
 *  
 *  /EsupMonitor?thread=1 : Display thread information (line mode)
 *  /EsupMonitor?thread=1&header=1 : Display headers for thread information (line mode)
 *  /EsupMonitor?thread=1&xml=1 : Display thread information (xml mode)
 *  /EsupMonitor?thread=1&xml=full : Display thread information (line mode) full for list thread stack
 *  
 *  /EsupMonitor?datasources=1 : Display datasources information (line mode) [must have com.sun.management.jmxremote]
 *  /EsupMonitor?datasources=1&header=1 : Display headers for datasources information (line mode) [must have com.sun.management.jmxremote]
 *  /EsupMonitor?datasources=1&xml=1 : Display datasources information (xml mode) [must have com.sun.management.jmxremote]
 *  
 *  /EsupMonitor?io : display IO informations
 **/
package org.esupportail.portal.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portal.spring.security.PortalPersonUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/EsupMonitor")
public class EsupMonitor {

	private static final Log log = LogFactory.getLog(EsupMonitor.class);
	private static int maxDumpUser = 100;
	private static String lsofCmd = "";

	private static String guestUsername = "guest";

	@Autowired
	protected SessionRegistry sessionRegistry;

	@RequestMapping
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer outXml = new StringBuffer();

		boolean thread = false;
		boolean threadFull = false;

		boolean io = false;

		boolean memory = true;
		boolean memoryFull = false;

		boolean datasources = false;
		boolean datasourcesFull = false;

		boolean xml = false;
		boolean header = false;

		if (request.getParameter("header") != null) {
			header = true;
		}

		if (request.getParameter("thread") != null) {
			thread = true;

			if (request.getParameter("xml") != null) {
				xml = true;
				if (request.getParameter("xml").equals("full")) {
					threadFull = true;
				}
			}
		} else if (request.getParameter("datasources") != null) {
			datasources = true;

			if (request.getParameter("xml") != null) {
				xml = true;
				if (request.getParameter("xml").equals("full")) {
					datasourcesFull = true;
				}
			}
		} else if (request.getParameter("io") != null) {
			io = true;
		} else if (request.getParameter("xml") != null) {
			xml = true;
			if (request.getParameter("xml").equals("full")) {
				memoryFull = true;
			}
		}

		if (thread) {
			thread(header, xml, threadFull, out, response);
		} else if (datasources) {
			datasources(header, xml, datasourcesFull, out, response);
		} else if (io) {
			io(out, response);
		} else if (memory) {
			memory(header, xml, memoryFull, out, response);
		}
	}

	private void memory(boolean header, boolean xml, boolean full,
			PrintWriter out, HttpServletResponse response) {
		StringBuffer outXml = new StringBuffer();

		/* MEMORY */
		long fmem = Runtime.getRuntime().freeMemory();
		long tmem = Runtime.getRuntime().totalMemory();
		long mmem = Runtime.getRuntime().maxMemory();

		/* CURRENT USER */
		Properties p = new Properties();

		try {
			p = grabUsersBySessions();
		} catch (Exception e) {
			log.error("EsupMonitor :doGet: ERROR : ", e);
		}

		Map sorted = new TreeMap(p);
		Iterator i = sorted.keySet().iterator();

		String worker = "";
		int sessionCount = 0;
		int guestCount = 0;
		int totalCount = 0;
		while (i.hasNext()) {
			worker = (String) i.next();
			if (worker.equals(guestUsername)) {
				guestCount += Integer.parseInt(p.getProperty(worker));
			} else {
				sessionCount += Integer.parseInt(p.getProperty(worker));
			}
		}
		totalCount = guestCount + sessionCount;

		/* FULL INFORMATION ON USER */
		Properties pFull = new Properties();
		if (full && maxDumpUser >= sessionCount) {
			pFull = p;
		}

		if (!xml) {
			if (header)
				out.println("FREE_MEMORY;TOTAL_MEMORY;MAX_MEMORY;AUTHENTICATED_SESSION;GUEST_SESSION");
			out.println(fmem + ";" + tmem + ";" + mmem + ";" + sessionCount
					+ ";" + guestCount);
		} else {
			outXml.append("<?xml version=\"1.0\"?>\n");
			outXml.append("<runtimeinfo>\n");

			outXml.append(" <memory free=\"" + fmem + "\" total=\"" + tmem
					+ "\" max=\"" + mmem + "\" />\n");
			outXml.append(" <sessions total=\"" + totalCount + "\" guest=\""
					+ guestCount + "\" connected=\"" + sessionCount + "\" />\n");
			outXml.append(" <users>\n");

			if (pFull != null) {
				sorted = new TreeMap(pFull);
				i = sorted.keySet().iterator();
				String username = null;
				String info = null;

				while (i.hasNext()) {
					username = (String) i.next();
					info = pFull.getProperty(username);
					// info = XMLEscaper.escape(info);
					outXml.append(" <user uid=\"" + username + "\" info=\""
							+ info + "\" />\n");
				}
			}
			outXml.append(" </users>\n");
			outXml.append("</runtimeinfo>\n");
			response.setContentType("text/xml");
			out.println(outXml.toString());
		}
	}

	private void thread(boolean header, boolean xml, boolean full,
			PrintWriter out, HttpServletResponse response) {
		StringBuffer outXml = new StringBuffer();
		ThreadMXBean t = ManagementFactory.getThreadMXBean();
		int deadLocked = 0;
		long ids[] = null;
		try {
			ids = t.findMonitorDeadlockedThreads();
			if (ids != null && ids.length != 0) {
				deadLocked = ids.length;
			}
		} catch (Exception e) {
			log.error(
					"EsupMonitor :doGet: ERROR : call to findMonitorDeadlockedThreads failed :",
					e);
		}
		long threads[] = t.getAllThreadIds();
		ThreadInfo[] tinfo = t.getThreadInfo(threads, 15);

		if (!xml) {
			int newState = 0;
			int runState = 0;
			int blockedState = 0;
			int waitState = 0;
			int timewaitState = 0;
			int endState = 0;

			for (int i = 0; i < tinfo.length; i++) {
				ThreadInfo e = tinfo[i];

				Thread.State currentState = e.getThreadState();
				long cpuUsed = (t.getThreadUserTime(e.getThreadId()) / 1000000000l);

				if (currentState.equals(Thread.State.NEW)) {
					newState++;
				} else if (currentState.equals(Thread.State.RUNNABLE)) {
					runState++;
				} else if (currentState.equals(Thread.State.BLOCKED)) {
					blockedState++;
				} else if (currentState.equals(Thread.State.WAITING)) {
					waitState++;
				} else if (currentState.equals(Thread.State.TIMED_WAITING)) {
					timewaitState++;
				} else if (currentState.equals(Thread.State.TERMINATED)) {
					endState++;
				}
			}

			if (header)
				out.println("NB_DEADLOCKED;NB_LOCKED;NB_RUNNABLE;NB_NEW;NB_WAITING;NB_TIMED_WAITING;NB_TERMINATED");
			out.println(deadLocked + ";" + blockedState + ";" + runState + ";"
					+ newState + ";" + waitState + ";" + timewaitState + ";"
					+ endState);
		} else {
			outXml.append("<?xml version=\"1.0\"?>\n");
			outXml.append("<threadinfo>\n");

			outXml.append("<deadlocked>\n");
			if (deadLocked != 0) {
				for (int n = 0; n < ids.length; n++) {
					outXml.append("<id>" + ids[n] + "</id>\n");
				}
			}
			outXml.append("</deadlocked>\n");

			outXml.append("<threads>\n");
			for (int i = 0; i < tinfo.length; i++) {
				outXml.append("<thread");

				ThreadInfo e = tinfo[i];

				StackTraceElement[] el = e.getStackTrace();
				// outXml.append(" name=\""+XMLEscaper.escape((String)
				// e.getThreadName())+"\"");
				outXml.append(" name=\"" + (String) e.getThreadName() + "\"");
				outXml.append(" id=\"" + e.getThreadId() + "\"");
				outXml.append(" state=\"" + e.getThreadState() + "\"");
				outXml.append(" cpuTime=\""
						+ (t.getThreadUserTime(e.getThreadId()) / 1000000000l)
						+ "\">\n");

				if (full) {
					StringBuffer stack = new StringBuffer();
					if (el == null || el.length == 0) {
						stack.append("no stack trace available");
					}

					for (int n = 0; n < el.length; n++) {
						StackTraceElement frame = el[n];

						if (frame == null) {
							stack.append("null stack frame");
							continue;
						}

						stack.append(frame.toString() + "\n");
					}
					outXml.append("<![CDATA[\n");
					// outXml.append(XMLEscaper.escape(stack.toString())+"\n");
					outXml.append(stack.toString() + "\n");
					outXml.append("]]>\n");
				}
				outXml.append("</thread>\n");
			}
			outXml.append("</threads>\n");
			outXml.append("</threadinfo>\n");
			response.setContentType("text/xml");
			out.println(outXml.toString());
		}
	}

	private void datasources(boolean header, boolean xml, boolean full,
			PrintWriter out, HttpServletResponse response) {
		StringBuffer outXml = new StringBuffer();

		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			Set dataSourcesTomcat = server.queryMBeans(new ObjectName(
					"Catalina:type=DataSource,*"), null);
			Set dataSourcesHibernate = server.queryMBeans(new ObjectName(
					"uPortal:section=Persistence,name=PortalDB"), null);

			if (!xml) {
				if (header)
					out.println("ORIGIN;NAME;URL;MAX_ACTIVE;NUM_ACTIVE;MIN_IDLE;NUM_IDLE");

				Iterator it = dataSourcesTomcat.iterator();
				while (it.hasNext()) {
					ObjectInstance element = (ObjectInstance) it.next();
					ObjectName objectName = element.getObjectName();

					out.print("TOMCAT; ");
					out.print(objectName.getKeyProperty("name") + "; ");
					out.print(server.getAttribute(objectName, "url") + ";");
					out.print(server.getAttribute(objectName, "maxActive")
							+ ";");
					out.print(server.getAttribute(objectName, "numActive")
							+ ";");
					out.print(server.getAttribute(objectName, "minIdle") + ";");
					out.print(server.getAttribute(objectName, "numIdle") + ";");
					out.println("");
				}

				it = dataSourcesHibernate.iterator();
				while (it.hasNext()) {
					ObjectInstance element = (ObjectInstance) it.next();
					ObjectName objectName = element.getObjectName();

					out.print("HIBERNATE; ");
					out.print(objectName.getKeyProperty("name") + "; ");
					out.print(server.getAttribute(objectName, "Url") + ";");
					out.print(server.getAttribute(objectName, "MaxActive")
							+ ";");
					out.print(server.getAttribute(objectName, "NumActive")
							+ ";");
					out.print(server.getAttribute(objectName, "MinIdle") + ";");
					out.print(server.getAttribute(objectName, "NumIdle") + ";");
					out.println("");
				}
			} else {
				outXml.append("<?xml version=\"1.0\"?>\n");
				outXml.append("<datasourceinfo>\n");

				outXml.append("<datasources>\n");
				Iterator it = dataSourcesTomcat.iterator();
				while (it.hasNext()) {
					ObjectInstance element = (ObjectInstance) it.next();
					ObjectName objectName = element.getObjectName();

					outXml.append("<datasource");
					outXml.append(" origin=\"TOMCAT\"");
					// outXml.append(" name=\""+XMLEscaper.escape((String)objectName.getKeyProperty("name"))+"\"");
					// outXml.append(" url=\""+XMLEscaper.escape((String)server.getAttribute(objectName,"url"))+"\"");
					outXml.append(" name=\""
							+ (String) objectName.getKeyProperty("name") + "\"");
					outXml.append(" url=\""
							+ (String) server.getAttribute(objectName, "url")
							+ "\"");
					outXml.append(" maxActive=\""
							+ server.getAttribute(objectName, "maxActive")
							+ "\"");
					outXml.append(" numActive=\""
							+ server.getAttribute(objectName, "numActive")
							+ "\"");
					outXml.append(" minIdle=\""
							+ server.getAttribute(objectName, "minIdle") + "\"");
					outXml.append(" numIdle=\""
							+ server.getAttribute(objectName, "numIdle") + "\"");
					outXml.append(">");
					outXml.append("</datasource>\n");
				}

				it = dataSourcesHibernate.iterator();
				while (it.hasNext()) {
					ObjectInstance element = (ObjectInstance) it.next();
					ObjectName objectName = element.getObjectName();

					outXml.append("<datasource");
					outXml.append(" origin=\"HIBERNATE\"");
					// outXml.append(" name=\""+XMLEscaper.escape((String)objectName.getKeyProperty("name"))+"\"");
					// outXml.append(" url=\""+XMLEscaper.escape((String)server.getAttribute(objectName,"Url"))+"\"");
					outXml.append(" name=\""
							+ (String) objectName.getKeyProperty("name") + "\"");
					outXml.append(" url=\""
							+ (String) server.getAttribute(objectName, "Url")
							+ "\"");
					outXml.append(" maxActive=\""
							+ server.getAttribute(objectName, "MaxActive")
							+ "\"");
					outXml.append(" numActive=\""
							+ server.getAttribute(objectName, "NumActive")
							+ "\"");
					outXml.append(" minIdle=\""
							+ server.getAttribute(objectName, "MinIdle") + "\"");
					outXml.append(" numIdle=\""
							+ server.getAttribute(objectName, "NumIdle") + "\"");
					outXml.append(">");
					outXml.append("</datasource>\n");
				}

				outXml.append("</datasources>\n");
				outXml.append("</datasourceinfo>\n");
				response.setContentType("text/xml");
				out.println(outXml.toString());
			}
		} catch (Exception e) {
			log.error(
					"EsupMonitor :doGet: ERROR : call to anagementFactory.getPlatformMBeanServer failed :",
					e);
		}
	}

	private void io(PrintWriter out, HttpServletResponse response) {
		StringBuffer outXml = new StringBuffer();

		String CATALINA_PID = System.getenv("CATALINA_PID");
		String PID = null;

		outXml.append("<?xml version=\"1.0\"?>\n");
		outXml.append("<IOinfo>\n");

		if (CATALINA_PID != null && !CATALINA_PID.equals("")) {
			try {
				BufferedReader fin = new BufferedReader(new FileReader(
						CATALINA_PID));
				PID = fin.readLine();
				fin.close();
			} catch (IOException e) {
				log.error("EsupMonitor :doGet: ERROR IOView : unable to read pid file :"
						+ CATALINA_PID);
			}
		} else {
			log.error("EsupMonitor :doGet: ERROR IOView : could not find pid env var CATALINA_PID");
		}

		if (PID != null && !PID.equals("") && lsofCmd != null
				&& !lsofCmd.equals("")) {
			String cmd = lsofCmd.replaceAll("%p", PID);

			try {
				Process pLsof = Runtime.getRuntime().exec(cmd);
				BufferedReader input = new BufferedReader(
						new InputStreamReader(pLsof.getInputStream()));
				String line;
				outXml.append("<lines>\n");

				while ((line = input.readLine()) != null) {
					outXml.append("<line>\n");
					outXml.append("<![CDATA[\n");
					// outXml.append(XMLEscaper.escape(line)+"\n");
					outXml.append(line + "\n");
					outXml.append("]]>\n");
					outXml.append("</line>\n");
				}
				outXml.append("</lines>\n");
				input.close();
			} catch (Exception e) {
				log.error("EsupMonitor :doGet: ERROR IOView : error when launch lsof cmd : "
						+ e);
			}

		} else {
			log.error("EsupMonitor :doGet: ERROR IOView : PID (" + PID
					+ ") or lsofCmd (" + lsofCmd + ") is empty");
		}

		outXml.append("</IOinfo>\n");
		response.setContentType("text/xml");
		out.println(outXml.toString());

	}

	private Properties grabUsersBySessions() throws NamingException {

		List<Object> users = sessionRegistry.getAllPrincipals();

		Properties pback = new Properties();
		for (Object userObj : users) {
			PortalPersonUserDetails user = (PortalPersonUserDetails) userObj;
			String userId = user.getUsername();
			pback.put(userId, String.valueOf(Integer.parseInt(pback
					.getProperty(userId, "0")) + 1));
		}
		return pback;
	}// countSessions


}
