/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.support.uportal;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.portal.ws.client.PortalGroup;
import org.esupportail.portal.ws.client.PortalGroupHierarchy;
import org.esupportail.portal.ws.client.PortalUser;
import org.esupportail.portal.ws.client.axis.UportalService;
import org.esupportail.portal.ws.client.axis.UportalServiceServiceLocator;
import org.esupportail.portal.ws.client.exceptions.PortalAccessException;
import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.esupportail.portal.ws.client.exceptions.PortalUserNotFoundException;
import org.esupportail.portal.ws.client.support.AbstractPortalService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

/** 
 * An implementation of PortalService that relies on a uPortal web service, as proposed by
 * the package esuup-portal-ws.
 */
public class BasicUportalServiceImpl extends AbstractPortalService implements InitializingBean {

	/**
	 * The default URL of the web service.
	 */
	private static final String DEFAULT_URL = "http://localhost/services/UportalService";
	
	/**
	 * Logger object.
	 */
	private final Log logger = LogFactory.getLog(BasicUportalServiceImpl.class);
	
	/**
	 * The URL of the web service.
	 */
	private String url;
	
	/**
	 * Bean constructor.
	 */
	public BasicUportalServiceImpl() {
		super();
	}
	
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (!StringUtils.hasText(url)) {
			url = DEFAULT_URL;
			logger.warn(getClass() + ": property url is not set, '" + url + "' will be used");
		}
	}

	/**
	 * Convert an array into a map of attributes.
	 * @return the converted value.
	 */
	protected Map<String, List<String>> toAttributeMap(
			final Object[] attributeMapArray) {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		for (Object object : attributeMapArray) {
			Object[] attributeArray = (Object[]) object;
			String attributeName = (String) attributeArray[0];
			Object[] attributeValues = (Object[]) attributeArray[1]; 
			List<String> values = new ArrayList<String>();
			for (Object attributeValue : attributeValues) {
				values.add((String) attributeValue);
			}
			result.put(attributeName, values);
		}
		return result;
	}

	/**
	 * Convert an array into a PortalUser.
	 * @return the converted value.
	 */
	protected PortalUser toPortalUser(
			final Object[] userArray) {
		return new PortalUser((String) userArray[0], toAttributeMap((Object []) userArray[1]));
	}

	/**
	 * Convert an array into a list of PortalUser.
	 * @param userArrayList
	 * @return the converted value.
	 */
	public List<PortalUser> toPortalUserList(
			final Object[] userArrayList) {
		List<PortalUser> result = new ArrayList<PortalUser>();
		for (Object userArrayItem : userArrayList) {
			Object[] userArray = (Object[]) userArrayItem;
			result.add(toPortalUser(userArray));
		}
		return result;
	}

	/**
	 * Convert an array into a PortalGroup.
	 * @param groupArray
	 * @return the converted value.
	 */
	protected PortalGroup toPortalGroup(
			final Object[] groupArray) {
		return new PortalGroup((String) groupArray[0], (String) groupArray[1]);
	}

	/**
	 * Convert an array into a list of PortalGroup.
	 * @param groupArrayList
	 * @return the converted value.
	 */
	public List<PortalGroup> toPortalGroupList(
			final Object[] groupArrayList) {
		List<PortalGroup> result = new ArrayList<PortalGroup>();
		for (Object groupArrayItem : groupArrayList) {
			Object[] groupArray = (Object[]) groupArrayItem;
			result.add(toPortalGroup(groupArray));
		}
		return result;
	}

	/**
	 * Convert an array into a PortalGroupHierarchy.
	 * @param groupHierarchyArray
	 * @return the converted value.
	 */
	protected PortalGroupHierarchy toPortalGroupHierarchy(
			final Object[] groupHierarchyArray) {
		PortalGroup portalGroup = toPortalGroup((Object []) groupHierarchyArray[0]);
		if (groupHierarchyArray[1] == null) {
			return new PortalGroupHierarchy(portalGroup, null);
		}
		List<PortalGroupHierarchy> hierarchies = new ArrayList<PortalGroupHierarchy>();
		for (Object hierarchyArray : (Object[]) groupHierarchyArray[1]) {
			hierarchies.add(toPortalGroupHierarchy((Object[]) hierarchyArray));
		}
		return new PortalGroupHierarchy(portalGroup, hierarchies);
	}

	/**
	 * @return a web service.
	 * @throws ServiceException 
	 */
	private UportalService getWebService() throws ServiceException {
		URL webServiceUrl;
		try {
			webServiceUrl = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("url [" + url + "] is malformed", e);
		}
		return new UportalServiceServiceLocator().getUportalService(webServiceUrl);
	}

	/**
	 * Wrap exceptions.
	 * Ugly hack but what else util Axis throws exceptions correctly?
	 */
	protected DataAccessException wrap(
			final Exception e) {
		if (e instanceof AxisFault) {
			if (e.getMessage() != null) {
				if (e.getMessage().contains("UportalServiceUserNotFoundException:")) {
					return new PortalUserNotFoundException(e);
				}
				if (e.getMessage().contains("UportalServiceGroupNotFoundException:")) {
					return new PortalGroupNotFoundException(e);
				}
			}
		}
		e.printStackTrace();
		if (e instanceof RemoteException) {
			return new PortalErrorException(e);
		}
		if (e instanceof ServiceException) {
			return new PortalAccessException(e);
		}
		return new PortalErrorException(e);
	}

	//////////////////////////////////////////////////////////
	// user methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUser(java.lang.String)
	 */
	public PortalUser getUser(
			final String userId) {
		try {
			return toPortalUser(getWebService().getUser(userId));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#searchUsers(java.lang.String)
	 */
	public List<PortalUser> searchUsers(final String token) 
	throws PortalErrorException, PortalUserNotFoundException {
		try {
			return toPortalUserList(getWebService().searchUsers(token));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	//////////////////////////////////////////////////////////
	// group methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupById(java.lang.String)
	 */
	public PortalGroup getGroupById(
			final String groupId) {
		try {
			return toPortalGroup(getWebService().getGroupById(groupId));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupByName(java.lang.String)
	 */
	public PortalGroup getGroupByName(
			final String groupName) {
		try {
			return toPortalGroup(getWebService().getGroupByName(groupName));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#searchGroupsByName(java.lang.String)
	 */
	public List<PortalGroup> searchGroupsByName(
			final String token) {
		try {
			return toPortalGroupList(getWebService().searchGroupsByName(token));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getSubGroupsById(java.lang.String)
	 */
	public List<PortalGroup> getSubGroupsById(
			final String groupId) 
			throws PortalErrorException, PortalGroupNotFoundException {
		try {
			return toPortalGroupList(getWebService().getSubGroupsById(groupId));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getSubGroupsByName(java.lang.String)
	 */
	public List<PortalGroup> getSubGroupsByName(
			final String groupName) 
			throws PortalErrorException, PortalGroupNotFoundException {
		try {
			return toPortalGroupList(getWebService().getSubGroupsByName(groupName));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	//////////////////////////////////////////////////////////
	// group hierarchy methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupHierarchyById(java.lang.String)
	 */
	public PortalGroupHierarchy getGroupHierarchyById(
			final String groupId) 
	throws PortalErrorException, PortalGroupNotFoundException {
		try {
			return toPortalGroupHierarchy(getWebService().getGroupHierarchyById(groupId));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupHierarchyByName(java.lang.String)
	 */
	public PortalGroupHierarchy getGroupHierarchyByName(
			final String groupName) 
	throws PortalErrorException, PortalGroupNotFoundException {
		try {
			return toPortalGroupHierarchy(getWebService().getGroupHierarchyByName(groupName));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getRootGroup()
	 */
	public PortalGroup getRootGroup() {
		try {
			return toPortalGroup(getWebService().getRootGroup());
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupHierarchy()
	 */
	public PortalGroupHierarchy getGroupHierarchy() {
		try {
			return toPortalGroupHierarchy(getWebService().getGroupHierarchy());
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	//////////////////////////////////////////////////////////
	// group membership methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getUserGroups(java.lang.String)
	 */
	public List<PortalGroup> getUserGroups(
			final String userId) {
		try {
			return toPortalGroupList(getWebService().getUserGroups(userId));
		} catch (Exception e) {
			throw wrap(e);
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#getGroupUsers(java.lang.String)
	 */
	public List<PortalUser> getGroupUsers(
			final String groupId) {
		throw new UnsupportedOperationException(
				"it is not possible to retrieve the users of a group in uPortal");
	}

	/**
	 * @see org.esupportail.portal.ws.client.PortalService#isUserMemberOfGroup(
	 * java.lang.String, java.lang.String)
	 */
	public boolean isUserMemberOfGroup(
			final String userId, 
			final String groupId) {
		try {
			return getWebService().isUserMemberOfGroup(userId, groupId);
		} catch (Exception e) {
			throw wrap(e);
		}
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

}
