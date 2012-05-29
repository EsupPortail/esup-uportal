package fr.runn.auth.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.runn.auth.cas.RunnCas20ProxyReceivingTicketValidationFilter;

@Controller
@RequestMapping("/shibLogin")
public class ShibLoginController {

    protected final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected RunnCas20ProxyReceivingTicketValidationFilter runnCasValidateFilter;

	@RequestMapping
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String queryString = request.getQueryString();
		String redirectUrl = "/Login";
		if(queryString != null)
			redirectUrl = redirectUrl + "?" + queryString;
			
		HttpSession session = request.getSession();
		session.invalidate();
		
		session = request.getSession(true);
		
		String casLoginUrl = runnCasValidateFilter.getCasLoginUrl(request);
		if(casLoginUrl != null)
			redirectUrl = casLoginUrl;
		
		log.info("ShibLogin is called, current session is now invalidated - we redirect user to : " + redirectUrl);
				
		return new ModelAndView("redirect:" + redirectUrl);
	}

}
