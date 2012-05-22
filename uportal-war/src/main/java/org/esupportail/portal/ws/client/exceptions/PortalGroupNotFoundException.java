/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.exceptions; 

import org.springframework.dao.DataRetrievalFailureException;

/**
 * This exception should be thrown when trying to retrieve non existant groups from the portal.
 */
public class PortalGroupNotFoundException extends DataRetrievalFailureException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = -1602887582674155473L;

	/**
	 * @param cause
	 */
	public PortalGroupNotFoundException(final Exception cause) {
		super(cause.getMessage());
	}

}
