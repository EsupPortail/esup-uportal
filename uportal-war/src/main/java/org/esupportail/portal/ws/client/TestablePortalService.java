/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client;

/** 
 * The interface of Portal services that can be tested.
 */
public interface TestablePortalService extends PortalService {

	/**
	 * Test the Portal connection.
	 */
	void test();
	
}
