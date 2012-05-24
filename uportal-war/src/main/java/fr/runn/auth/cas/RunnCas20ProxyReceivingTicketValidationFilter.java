package fr.runn.auth.cas;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.portal.security.mvc.LoginController;

import fr.runn.RunnUtils;

public class RunnCas20ProxyReceivingTicketValidationFilter implements Filter {

    protected final Log log = LogFactory.getLog(this.getClass());

	List<RunnTicketValidationFiltersWrapper> ticketValidationFiltersWrapper;
	
	private String loginUrl;
	
	private String logoutUrl;

	private String logoutUrlNoEncoded;

	public List<RunnTicketValidationFiltersWrapper> getTicketValidationFiltersWrapper() {
		return ticketValidationFiltersWrapper;
	}

	public void setTicketValidationFiltersWrapper(
			List<RunnTicketValidationFiltersWrapper> ticketValidationFiltersWrapper) {
		this.ticketValidationFiltersWrapper = ticketValidationFiltersWrapper;
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public void setLogoutUrlNoEncoded(String logoutUrlNoEncoded) {
		this.logoutUrlNoEncoded = logoutUrlNoEncoded;
	}

	public String getLogoutUrlNoEncoded() {
		return logoutUrlNoEncoded;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        final String requestUri = httpRequest.getRequestURI();
        
		boolean filterOk = false;
		boolean isSwap = false;

		// if swap - no cas filter
		HttpSession s = httpRequest.getSession(false);
		if(s != null) {
			if(s.getAttribute(LoginController.SWAP_ORIGINAL_UID) != null || s.getAttribute(LoginController.SWAP_TARGET_UID) != null) {
				isSwap = true;
			}
		}
 	    
 	    
		String client = httpRequest.getRemoteAddr();
		
		log.debug("Client Addr in RunnCas20ProxyReceivingTicketValidationFilter: " + client);
		log.debug("requestUri = " + requestUri);
		
		if(!isSwap && requestUri.contains("/CasProxyServlet")) {
			for (RunnTicketValidationFiltersWrapper filterWrapper : ticketValidationFiltersWrapper) {
				if (filterWrapper.getCasAddrs().contains(client)) {
					log.debug("The CAS server is calling ... we call TicketValidationFilter.doFilter" );
					filterWrapper.getTicketValidationFilter().doFilter(request,
						response, chain);
					filterOk = true;
					// no 404 file -> we send something ...
					java.io.PrintWriter resp_out = response.getWriter();
					resp_out.print("...");           
					filterOk = true;
				}
			}
		}
		
		
		if (!isSwap && !filterOk) {

			String univKey = RunnUtils.getAffiliationSuffixFromHeaderAttributes(httpRequest, null);

			log.debug("univKey = " + univKey);
			if (univKey != null && !univKey.isEmpty()) {
				for (RunnTicketValidationFiltersWrapper filterWrapper : ticketValidationFiltersWrapper) {
					if (filterWrapper.getAffiliationSuffix().equals(univKey)) {
						log.debug("univKey correspond to a CAS Server in config files ... ");
				        
						final String ticket = CommonUtils.safeGetParameter(httpRequest, filterWrapper.getTicketValidationFilter().getArtifactParameterName());
				        if (CommonUtils.isBlank(ticket)) {
				        	log.debug("there is no cas ticket in session, so we redirect to /shibLogin");
				        	String redirectUrl = "/uPortal/shibLogin";
				        	String queryString = httpRequest.getQueryString();
				        	if(queryString != null && queryString != "")
				        		redirectUrl = redirectUrl + "?" + queryString;
				        	httpResponse.sendRedirect(redirectUrl);
				        	filterOk = true;
				        } else {
				        	log.debug("we call TicketValidationFilter.doFilter");
				        	filterWrapper.getTicketValidationFilter().doFilter(
								request, response, chain);
				        	filterOk = true;
				        }
					}
				}
			}
		}

		if (!filterOk)
			chain.doFilter(request, response);

	}

	public String getCasLoginUrl(HttpServletRequest request) {
		
		String casLoginUrl = null;
	
		String univKey = RunnUtils.getAffiliationSuffixFromHeaderAttributes(request, null);
		if (univKey != null && !univKey.isEmpty()) 
			for (RunnTicketValidationFiltersWrapper filterWrapper : ticketValidationFiltersWrapper) 
				if (filterWrapper.getAffiliationSuffix().equals(univKey)) {
					casLoginUrl = filterWrapper.getCasLoginUrl();
					if(casLoginUrl != null)
						casLoginUrl = casLoginUrl.trim() + "?service=" + loginUrl;
						String queryString = request.getQueryString();
						if(queryString != null) {
							//casLoginUrl = casLoginUrl + "?" + queryString.replaceAll("&", "%26");
							queryString = queryString.replaceAll("%", "%25");
							queryString = queryString.replaceAll("&", "%26");
							casLoginUrl = casLoginUrl + "?" + queryString;
						}
							
				}

		return casLoginUrl;
	}

	public String getCasLogoutUrl(HttpServletRequest request) {
		
		String casLogoutUrl = null;
		
		String univKey = RunnUtils.getAffiliationSuffixFromHeaderAttributes(request, null);
		if (univKey != null && !univKey.isEmpty()) 
			for (RunnTicketValidationFiltersWrapper filterWrapper : ticketValidationFiltersWrapper) 
				if (filterWrapper.getAffiliationSuffix().equals(univKey)) {
					casLogoutUrl = filterWrapper.getCasLogoutUrl();
					if(casLogoutUrl != null)
						casLogoutUrl = casLogoutUrl.trim() + "?service=" + logoutUrl;
				}

		return casLogoutUrl;
		
	}

}
