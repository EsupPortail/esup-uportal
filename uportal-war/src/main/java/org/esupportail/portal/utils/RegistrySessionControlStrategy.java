package org.esupportail.portal.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.util.Assert;

public class RegistrySessionControlStrategy implements SessionAuthenticationStrategy {
   
	private final SessionRegistry sessionRegistry;
	
    public RegistrySessionControlStrategy(SessionRegistry sessionRegistry) {
        Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null");
        this.sessionRegistry = sessionRegistry;
    }
    
	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response)
			throws SessionAuthenticationException {
        sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal());
	}

}
