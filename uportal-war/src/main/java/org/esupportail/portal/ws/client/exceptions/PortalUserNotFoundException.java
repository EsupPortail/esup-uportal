/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.exceptions; 

import org.springframework.dao.DataRetrievalFailureException;

/**
 * This exception should be thrown when trying to retrieve non existant users from the portal.
 */
public class PortalUserNotFoundException extends DataRetrievalFailureException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = -1494233490740304427L;

	/**
	 * @param cause
	 */
	public PortalUserNotFoundException(final Exception cause) {
		super(cause.getMessage());
	}

}
