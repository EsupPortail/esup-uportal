package org.esupportail.portal.ws.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jasig.portal.EntityIdentifier;
import org.jasig.portal.groups.GroupsException;
import org.jasig.portal.groups.IEntity;
import org.jasig.portal.groups.IEntityGroup;
import org.jasig.portal.groups.IGroupConstants;
import org.jasig.portal.groups.IGroupMember;
import org.jasig.portal.security.IPerson;
import org.jasig.portal.services.GroupService;
import org.jasig.portal.spring.locator.PersonAttributeDaoLocator;

/**
 * The uPortal web service for portlets.
 */
public class UportalService {

	/**
	 * Constructor.
	 */
	public UportalService() {
		super();
	}

	//////////////////////////////////////////////////////////
	// user methods
	//////////////////////////////////////////////////////////

	/**
	 * @param userId
	 * @return the uPortal user with id userId.
	 */
	private IEntity getUportalUser(
			final String userId) 
	throws UportalServiceErrorException, UportalServiceUserNotFoundException {
		IEntity user;
		try {
			user = GroupService.getEntity(userId, IPerson.class);
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		if (user == null) {
			throw UportalServiceUserNotFoundException.userIdNotFound(userId);
		}
		return user;
	}

	/**
	 * @param userId
	 * @return all the attributes of the user with id userId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceUserNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List<String>> getUportalUserAttributes(final String userId)
	throws UportalServiceErrorException, UportalServiceUserNotFoundException {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		Map<String, Object> portalAttributes = 
			PersonAttributeDaoLocator.getPersonAttributeDao().getUserAttributes(userId);
		if (portalAttributes == null) {
			throw UportalServiceUserNotFoundException.userIdNotFound(userId);
		}
		Iterator<String> keyIterator = portalAttributes.keySet().iterator();
		if (!keyIterator.hasNext()) {
			throw UportalServiceUserNotFoundException.userIdNotFound(userId);
		}
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Object portalAttribute = portalAttributes.get(key);
			List<String> valueList = new ArrayList<String>();
			if (portalAttribute != null) {
				if (portalAttribute instanceof List) {
					for (Object listItem : (List) portalAttribute) {
						if (listItem instanceof String) {
							valueList.add((String) listItem);
						} else {
							valueList.add(listItem.toString());
						}
					}
				} else if (portalAttribute instanceof String) {
					valueList.add((String) portalAttribute);
				} else {
					valueList.add(portalAttribute.toString());
				}
			}
			result.put(key, valueList);
		}
		return result;
	}
	
//	/**
//	 * @return a string that represents the object.
//	 */
//	private static String objectToString(final Object object) {
//		if (object == null) {
//			return null;
//		}
//		if (!(object instanceof Object[])) {
//			return "[" + object.toString() + "]";
//		}
//		Object [] objects = (Object[]) object;
//		String separator = "";
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < objects.length; i++) {
//			sb.append(separator).append(i).append(" => ").append(objectToString(objects[i]));
//			separator = " ";
//		}
//		return sb.toString();
//	}
	
	/**
	 * Debug the result of a method.
	 */
	private static void debugResult(
			@SuppressWarnings("unused")
			final String methodName, 
			@SuppressWarnings("unused")
			final Object result) {
//		LogFactory.getLog(UportalService.class).info(methodName + "(): " + objectToString(result));
	}
	
	/**
	 * @return an array that represent the attribute.
	 */
	private static Object[] toArray(
			final String name,
			final List<String> values) {
		Object [] result = new Object[2];
		result[0] = name;
		Object [] valueListArray = new Object[values.size()];
		int i = 0;
		for (String value : values) {
			valueListArray[i] = value;
			i++;
		}
		result[1] = valueListArray;
		return result;
	}
	
	/**
	 * @return the group converted to an object array (to pass through web services).
	 */
	private static Object[] toArray(
			final String userId,
			final Map<String, List<String>> attributes) {
		Object [] result = new Object[2];
		result[0] = userId;
		Object [] attributeListArray = new Object[attributes.size()];
		Iterator<String> attributeNameIter = attributes.keySet().iterator();
		int i = 0;
		while (attributeNameIter.hasNext()) {
			String attributeName = attributeNameIter.next();
			attributeListArray[i] = toArray(attributeName, attributes.get(attributeName));
			i++;
		}
		result[1] = attributeListArray;
		return result;
	}
	
	/**
	 * This method returns a 2-array:
	 * 0: the user's id
	 * 1: an array to store the user's attributes
	 * @param userId
	 * @return the user that corresponds to userId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceUserNotFoundException 
	 */
	public Object [] getUser(final String userId)
	throws UportalServiceErrorException, UportalServiceUserNotFoundException {
		Object [] result = toArray(userId, getUportalUserAttributes(userId));
		debugResult("getUser", result);
		return result;
	}
	
 	/**
	 * @param token 
	 * @return the id of the users of which name contains token.
	 * @throws GroupsException 
	 */
	@SuppressWarnings("unchecked")
	private List<String> searchUportalUsers(final String token) 
	throws UportalServiceErrorException {
		try {
			EntityIdentifier [] userIds = GroupService.searchForEntities(
					token, IGroupConstants.CONTAINS, IPerson.class);
			List<String> result = new ArrayList<String>();
			if (userIds != null) {
				for (EntityIdentifier userId : userIds) {
					result.add(userId.getKey());
				}
			}
			return result;
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param token
	 * @return the groups of which name contains token.
	 * @throws UportalServiceErrorException 
	 */
	public Object[] searchUsers(
			final String token) 
	throws UportalServiceErrorException {
		List<String> userIds = searchUportalUsers(token);
		Object [] result = new Object[userIds.size()];
		for (int i = 0; i < userIds.size(); i++) {
			result[i] = getUser(userIds.get(i));
		}
		debugResult("searchUsers", result);
		return result;
	}

	//////////////////////////////////////////////////////////
	// group methods
	//////////////////////////////////////////////////////////

	/**
	 * @return the group converted to an object array (to pass through web services).
	 */
	private static Object[] toArray(final IEntityGroup group) {
		Object[] groupArray = new Object[2];
		groupArray[0] = group.getKey();
		groupArray[1] = group.getName();
		return groupArray;
	}
	
	/**
	 * @param groupId 
	 * @return the group with id groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	private static IEntityGroup getUportalGroupById(
			final String groupId) 
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		IEntityGroup group;
		try {
			group = GroupService.findGroup(groupId);
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		if (group == null) {
			throw UportalServiceGroupNotFoundException.groupIdNotFound(groupId);
		}
		return group;
	}

	/**
	 * The result of this method is a 2-array:
	 * 0: the id of the group.
	 * 1: the name of the group.
	 * @param groupId
	 * @return the group with id groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	public Object [] getGroupById(final String groupId)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = toArray(getUportalGroupById(groupId));
		debugResult("getGroupById", result);
		return result;
	}
	
	/**
	 * The result of this method is a 2-array:
	 * 0: the id of the group.
	 * 1: the name of the group.
	 * @param groupName
	 * @return the group with name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	private IEntityGroup getUportalGroupByName(final String groupName)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		try {
			EntityIdentifier [] groupIds = GroupService.searchForGroups(
					groupName, IGroupConstants.IS, IPerson.class);
			if (groupIds == null || groupIds.length == 0) {
				throw UportalServiceGroupNotFoundException.groupNameNotFound(groupName);
			}
			return GroupService.findGroup(groupIds[0].getKey());
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
	}
	
	/**
	 * The result of this method is a 2-array:
	 * 0: the id of the group.
	 * 1: the name of the group.
	 * @param groupName
	 * @return the group with name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	public Object [] getGroupByName(final String groupName)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = toArray(getUportalGroupByName(groupName));
		debugResult("getGroupByName", result);
		return result;
	}
	
 	/**
	 * @param token 
	 * @return the groups of which name contains token.
	 * @throws GroupsException 
	 */
	@SuppressWarnings("unchecked")
	private List<IEntityGroup> searchUportalGroupsByName(final String token) 
	throws UportalServiceErrorException {
		try {
			EntityIdentifier [] groupIds = GroupService.searchForGroups(
					token, IGroupConstants.CONTAINS, IPerson.class);
			List<IEntityGroup> groups = new ArrayList<IEntityGroup>();
			if (groupIds != null) {
				for (EntityIdentifier groupId : groupIds) {
					groups.add(GroupService.findGroup(groupId.getKey()));
				}
			}
			return groups;
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param token
	 * @return the groups of which name contains token.
	 * @throws UportalServiceErrorException 
	 */
	public Object[] searchGroupsByName(final String token) 
	throws UportalServiceErrorException {
		List<IEntityGroup> groups = searchUportalGroupsByName(token);
		Object [] result = new Object[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			result[i] = toArray(groups.get(i));
		}
		debugResult("searchGroupsByName", result);
		return result;
	}

	//////////////////////////////////////////////////////////
	// group hierarchy methods
	//////////////////////////////////////////////////////////

	/**
	 * The result of this method is a 2-array:
	 * 0: the id of the group.
	 * 1: the name of the group.
	 * @param groupName
	 * @return the group with distinguished name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	private IEntityGroup getUportalGroupByDistinguishedName(final String groupName)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		IEntityGroup group;
		try {
			group = GroupService.getDistinguishedGroup(groupName);
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		if (group == null) {
			throw UportalServiceGroupNotFoundException.groupNameNotFound(groupName);
		}
		return group;
	}
	
	/**
	 * @return the root group.
	 */
	private IEntityGroup getUportalRootGroup() {
		return getUportalGroupByDistinguishedName(IGroupConstants.EVERYONE);
	}

	/**
	 * The result of this method is a 2-array:
	 * 0: the id of the root group.
	 * 1: the name of the root group.
	 * @return the root group.
	 */
	public Object [] getRootGroup() {
		Object [] result = toArray(getUportalRootGroup());
		debugResult("getRootGroup", result);
		return result;
	}

	/**
	 * @param group 
	 * @return the sub groups of a group.
	 * @throws GroupsException 
	 */
	@SuppressWarnings("unchecked")
	private List<IEntityGroup> getUportalSubGroups(final IEntityGroup group) 
	throws UportalServiceErrorException {
		List<IEntityGroup> subGroups = new ArrayList<IEntityGroup>();
		Iterator<IGroupMember> groupMemberIter;
		try {
			groupMemberIter = group.getMembers();
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		while (groupMemberIter.hasNext()) {
			IGroupMember groupMember = groupMemberIter.next();
			if (groupMember instanceof IEntityGroup) {
				subGroups.add((IEntityGroup) groupMember);
			}
		}
		return subGroups;
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param group 
	 * @return the sub groups of a group.
	 * @throws GroupsException 
	 */
	@SuppressWarnings("unchecked")
	private Object[] getSubGroups(final IEntityGroup group) 
	throws UportalServiceErrorException {
		List<IEntityGroup> groups = getUportalSubGroups(group);
		Object [] result = new Object[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			result[i] = toArray(groups.get(i));
		}
		return result;
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param groupId
	 * @return the sub groups of the group with id groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object[] getSubGroupsById(final String groupId) 
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getSubGroups(getUportalGroupById(groupId));
		debugResult("getSubGroupsById", result);
		return result;
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param groupName
	 * @return the sub groups of the group with name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object[] getSubGroupsByName(final String groupName) 
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getSubGroups(getUportalGroupByName(groupName));
		debugResult("getSubGroupsByName", result);
		return result;
	}
	
	/**
	 * @param group 
	 * @return the containing groups of a group.
	 * @throws GroupsException 
	 */
	private List<IEntityGroup> getUportalContainingGroups(final IEntityGroup group) 
	throws UportalServiceErrorException {
		List<IEntityGroup> containingGroups = new ArrayList<IEntityGroup>();
		Iterator<IEntityGroup> containingGroupsIter;
		try {
			containingGroupsIter = group.getContainingGroups();
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		while (containingGroupsIter.hasNext()) {
			IEntityGroup containingGroup = containingGroupsIter.next();
			containingGroups.add(containingGroup);
		}
		return containingGroups;
	}
	
	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param group 
	 * @return the containing groups of a group.
	 * @throws GroupsException 
	 */
	@SuppressWarnings("unchecked")
	private Object[] getContainingGroups(final IEntityGroup group) 
	throws UportalServiceErrorException {
		List<IEntityGroup> groups = getUportalContainingGroups(group);
		Object [] result = new Object[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			result[i] = toArray(groups.get(i));
		}
		return result;
	}
	
	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param groupId
	 * @return the containing groups of the group with id groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object[] getContainingGroupsById(final String groupId) 
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getContainingGroups(getUportalGroupById(groupId));
		debugResult("getContainingGroupsById", result);
		return result;
	}

	/**
	 * This method returns an array of 2-array objects:
	 * 0: the group id
	 * 1: the group name
	 * @param groupName
	 * @return the sub groups of the group with name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object[] getContainingGroupsByName(final String groupName) 
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getContainingGroups(getUportalGroupByName(groupName));
		debugResult("getContainingGroupsByName", result);
		return result;
	}


	/**
	 * @param group 
	 * @return the hierarchy of a group.
	 * @throws UportalServiceErrorException 
	 */
	@SuppressWarnings("unchecked")
	private UportalGroupHierarchy getGroupHierarchy(final IEntityGroup group)
	throws UportalServiceErrorException {
		List<UportalGroupHierarchy> subHierarchies = new ArrayList<UportalGroupHierarchy>();
		for (IEntityGroup subGroup : getUportalSubGroups(group)) {
			subHierarchies.add(getGroupHierarchy(subGroup));
		}
		if (subHierarchies.size() == 0) {
			return new UportalGroupHierarchy(group, null);
		}
		return new UportalGroupHierarchy(group, subHierarchies);
	}

	/**
	 * This method returns a 2-array object:
	 * 0: a 2-array object (0: the group id, 1: the group name).
	 * 1: an array of group hierarchies (null if no sub group).
	 * @param groupId
	 * @return the hierarchy of the group with id groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	public Object[] getGroupHierarchyById(final String groupId)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getGroupHierarchy(getUportalGroupById(groupId)).toArray();
		debugResult("getGroupHierarchyById", result);
		return result;
	}

	/**
	 * This method returns a 2-array object:
	 * 0: a 2-array object (0: the group id, 1: the group name).
	 * 1: an array of group hierarchies (null if no sub group).
	 * @param groupName
	 * @return the hierarchy of the group with name groupName.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	public Object[] getGroupHierarchyByName(final String groupName)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getGroupHierarchy(getUportalGroupByName(groupName)).toArray();
		debugResult("getGroupHierarchyByName", result);
		return result;
	}

	/**
	 * @return the complete group hierarchy.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 */
	public Object[] getGroupHierarchy()
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException {
		Object [] result = getGroupHierarchy(getUportalRootGroup()).toArray();
		debugResult("getGroupHierarchy", result);
		return result;
	}

	//////////////////////////////////////////////////////////
	// group membership methods
	//////////////////////////////////////////////////////////

	/**
	 * @return the groups the user with id userId belongs to, as an array.
	 * Each entry of the array represents a group:
	 * 0: the id of the group.
	 * 1: the name of the group.
	 * @param userId
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceUserNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object [] getUserGroups(final String userId)
	throws UportalServiceErrorException, UportalServiceUserNotFoundException {
		Iterator<IGroupMember> groupIter;
		try {
			groupIter = getUportalUser(userId).getAllContainingGroups();
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		List<IEntityGroup> groups = new ArrayList<IEntityGroup>(); 
		while (groupIter.hasNext()) {
			IGroupMember groupMember = groupIter.next();
			if (groupMember instanceof IEntityGroup) {
				groups.add((IEntityGroup) groupMember);
			}
		}
		Object [] result = new Object[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			result[i] = toArray(groups.get(i));
		}
		debugResult("getUserGroups", result);
		return result;
	}
	
	/**
	 * @param userId 
	 * @param groupId 
	 * @return true if the user with id userId belongs to the group with group groupId.
	 * @throws UportalServiceErrorException 
	 * @throws UportalServiceGroupNotFoundException 
	 * @throws UportalServiceUserNotFoundException 
	 */
	public boolean isUserMemberOfGroup(final String userId, final String groupId)
	throws UportalServiceErrorException, UportalServiceGroupNotFoundException, 
	UportalServiceUserNotFoundException {
		boolean result;
		try {
			result = getUportalGroupById(groupId).deepContains(getUportalUser(userId));
		} catch (GroupsException e) {
			throw UportalServiceErrorException.error(e);
		}
		debugResult("isUserMemberOfGroup", result);
		return result;
	}
	
}

