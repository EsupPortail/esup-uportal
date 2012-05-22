/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client;

import java.util.List;
import java.util.Map;

import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.esupportail.portal.ws.client.exceptions.PortalUserNotFoundException;

/** 
 * The interface of Portal services, used for instance to retrieve group information.
 */
public interface PortalService {

	//////////////////////////////////////////////////////////
	// user methods
	//////////////////////////////////////////////////////////

	/**
	 * @param userId
	 * @return the user with id userId.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	PortalUser getUser(String userId)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param token
	 * @return the users of which the id contains token.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	List<PortalUser> searchUsers(String token)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param userId
	 * @return a map of all the attributes of the user with id userId.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	Map<String, List<String>> getUserAttributes(String userId)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param user
	 * @return a map of all the attributes of the user.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	Map<String, List<String>> getUserAttributes(PortalUser user)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param userId
	 * @param attributeName 
	 * @return the values of the attribute attributeName for the user with id userId.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException
	 */
	List<String> getUserAttributeValues(String userId, String attributeName)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param user
	 * @param attributeName 
	 * @return the values of the attribute attributeName for the user.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException
	 */
	List<String> getUserAttributeValues(PortalUser user, String attributeName)
	throws PortalErrorException, PortalUserNotFoundException;
	
	//////////////////////////////////////////////////////////
	// group methods
	//////////////////////////////////////////////////////////

	/**
	 * @param groupId
	 * @return the group with id groupId.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroup getGroupById(String groupId)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param groupName
	 * @return the group with name groupName.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroup getGroupByName(String groupName)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param token
	 * @return the groups of which name matches token.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalGroup> searchGroupsByName(String token)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param groupId
	 * @return the sub groups of the group with id groupId.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalGroup> getSubGroupsById(String groupId)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param groupName
	 * @return the sub groups of the group with name groupName.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalGroup> getSubGroupsByName(String groupName)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param group
	 * @return the sub groups of the group.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalGroup> getSubGroups(PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	//////////////////////////////////////////////////////////
	// group hierarchy methods
	//////////////////////////////////////////////////////////

	/**
	 * @param groupId 
	 * @return the group hierarchy of the group with id groupId. 
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroupHierarchy getGroupHierarchyById(final String groupId)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param groupName
	 * @return the group hierarchy of the group with name groupName. 
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroupHierarchy getGroupHierarchyByName(final String groupName)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param group
	 * @return the group hierarchy of the group. 
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroupHierarchy getGroupHierarchy(final PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @return the root group.
	 */
	PortalGroup getRootGroup();

	/**
	 * Return the complete group hierarchy of the portal. <b>Important: calling this method, 
	 * even with cached implementation, can have a significant impact on the performances 
	 * of your portal and applications</b>. 
	 * @return the complete group hierarchy.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	PortalGroupHierarchy getGroupHierarchy()
	throws PortalErrorException, PortalGroupNotFoundException;
	
	//////////////////////////////////////////////////////////
	// group membership methods
	//////////////////////////////////////////////////////////

	/**
	 * @param userId
	 * @return the groups the user with id userId belongs to.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	List<PortalGroup> getUserGroups(String userId)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param user
	 * @return the groups the user belongs to.
	 * @throws PortalErrorException 
	 * @throws PortalUserNotFoundException 
	 */
	List<PortalGroup> getUserGroups(PortalUser user)
	throws PortalErrorException, PortalUserNotFoundException;
	
	/**
	 * @param groupId
	 * @return the users that belong to the group with id groupId.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalUser> getGroupUsers(String groupId)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param group
	 * @return the users that belong to the group.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 */
	List<PortalUser> getGroupUsers(PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException;
	
	/**
	 * @param userId 
	 * @param groupId 
	 * @return true if the user with id userId belongs to the group with group groupId.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 * @throws PortalUserNotFoundException 
	 */
	boolean isUserMemberOfGroup(String userId, String groupId)
	throws PortalErrorException, PortalGroupNotFoundException, PortalUserNotFoundException;
	
	/**
	 * @param user
	 * @param group
	 * @return true if the user belongs to the group.
	 * @throws PortalErrorException 
	 * @throws PortalGroupNotFoundException 
	 * @throws PortalUserNotFoundException 
	 */
	boolean isUserMemberOfGroup(PortalUser user, PortalGroup group)
	throws PortalErrorException, PortalGroupNotFoundException, PortalUserNotFoundException;
	
}
