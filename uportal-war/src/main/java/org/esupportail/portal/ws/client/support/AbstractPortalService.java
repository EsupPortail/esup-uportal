/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.support;

import java.util.List;
import java.util.Map;

import org.esupportail.portal.ws.client.PortalGroup;
import org.esupportail.portal.ws.client.PortalGroupHierarchy;
import org.esupportail.portal.ws.client.PortalService;
import org.esupportail.portal.ws.client.PortalUser;
import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.esupportail.portal.ws.client.exceptions.PortalUserNotFoundException;



/** 
 * An abstract implementation of PortalService, to low the number of methods to 
 * write for concrete implementations.
 */
public abstract class AbstractPortalService implements PortalService {

	/**
	 * Bean constructor.
	 */
	protected AbstractPortalService() {
		super();
	}

	//////////////////////////////////////////////////////////
	// user methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserAttributes(java.lang.String)
	 */
	public Map<String, List<String>> getUserAttributes(
			final String userId) {
		return getUser(userId).getAttributes();
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserAttributes(
	 * org.esupportail.portal.ws.client.PortalUser)
	 */
	public Map<String, List<String>> getUserAttributes(
			final PortalUser user)
	throws PortalErrorException, PortalUserNotFoundException {
		return user.getAttributes();
	}
	
	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserAttributeValues(
	 * java.lang.String, java.lang.String)
	 */
	public List<String> getUserAttributeValues(
			final String userId, 
			final String attributeName) {
		return getUser(userId).getAttributeValues(attributeName);
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserAttributeValues(
	 * org.esupportail.portal.ws.client.PortalUser, java.lang.String)
	 */
	public List<String> getUserAttributeValues(
			final PortalUser user, 
			final String attributeName)
	throws PortalErrorException, PortalUserNotFoundException {
		return user.getAttributeValues(attributeName);
	}
	
	//////////////////////////////////////////////////////////
	// group methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getSubGroups(
	 * org.esupportail.portal.ws.client.PortalGroup)
	 */
	public List<PortalGroup> getSubGroups(final PortalGroup group) 
	throws PortalErrorException, PortalGroupNotFoundException {
		return getSubGroupsById(group.getId());
	}

	//////////////////////////////////////////////////////////
	// group hierarchy methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupHierarchy(
	 * org.esupportail.portal.ws.client.PortalGroup)
	 */
	public PortalGroupHierarchy getGroupHierarchy(PortalGroup group) throws PortalErrorException, PortalGroupNotFoundException {
		return getGroupHierarchyById(group.getId());
	}

	//////////////////////////////////////////////////////////
	// group membership methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserGroups(
	 * org.esupportail.portal.ws.client.PortalUser)
	 */
	public List<PortalGroup> getUserGroups(
			final PortalUser user)
	throws PortalErrorException, PortalUserNotFoundException {
		return getUserGroups(user.getId());
	}
	
	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupUsers(
	 * org.esupportail.portal.ws.client.PortalGroup)
	 */
	public List<PortalUser> getGroupUsers(
			final PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException {
		return getGroupUsers(group.getId());
	}
	
	/**
	 * @see org.esupportail.portal.ws.client.PortalService#isUserMemberOfGroup(
	 * org.esupportail.portal.ws.client.PortalUser, org.esupportail.portal.ws.client.PortalGroup)
	 */
	public boolean isUserMemberOfGroup(
			final PortalUser user, 
			final PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException, PortalUserNotFoundException {
		return isUserMemberOfGroup(user.getId(), group.getId());
	}

}
