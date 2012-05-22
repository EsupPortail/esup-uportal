/**
 * ESUP-Portail Portal Web Service - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-portal-ws
 */
package org.esupportail.portal.ws.client.exceptions; 

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * This exception should be thrown when unknown errors occur while using a portal service.
 */
public class PortalErrorException extends UncategorizedDataAccessException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = 4532546372686048320L;

	/**
	 * @param cause
	 */
	public PortalErrorException(final Exception cause) {
		super(cause.getMessage(), cause);
	}

}
