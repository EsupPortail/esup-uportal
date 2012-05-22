/**
 * ESUP-Portail Portal Web Service - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-portal-ws
 */
package org.esupportail.portal.ws.client.exceptions; 

import org.springframework.dao.DataAccessResourceFailureException;

/**
 * This exception should be thrown when unknown errors occur when accessing portal service.
 */
public class PortalAccessException extends DataAccessResourceFailureException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = 4532546372686048320L;

	/**
	 * @param cause
	 */
	public PortalAccessException(final Exception cause) {
		super(cause.getMessage(), cause);
	}

}
