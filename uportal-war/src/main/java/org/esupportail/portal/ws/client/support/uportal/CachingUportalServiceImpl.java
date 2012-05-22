/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.support.uportal;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.portal.ws.client.PortalGroup;
import org.esupportail.portal.ws.client.PortalGroupHierarchy;
import org.esupportail.portal.ws.client.PortalUser;
import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/** 
 * An implementation of PortalService that relies on a uPortal web service, which 
 * is testable and caches the requests.
 */
public class CachingUportalServiceImpl extends TestableUportalServiceImpl {

	/**
	 * The default name for the cache.
	 */
	private static final String DEFAULT_CACHE_NAME = CachingUportalServiceImpl.class.getName();

	/**
	 * the cache.
	 */
	private Cache cache;

	/**
	 * the name of the cache.
	 */
	private String cacheName;

	/**
	 * the cacheManager.
	 */
	private CacheManager cacheManager;

	/**
	 * A logger.
	 */
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Bean constructor.
	 */
	public CachingUportalServiceImpl() {
		super();
	}

	/**
	 * set the default cacheName.
	 */
	protected void setDefaultCacheName() {
		this.cacheName = DEFAULT_CACHE_NAME;
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.TestableUportalServiceImpl#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		if (!StringUtils.hasText(cacheName)) {
			setDefaultCacheName();
			logger.warn(getClass() + ": no cacheName attribute set, '" 
					+ cacheName + "' will be used");
		}
		Assert.notNull(cacheManager, 
				"property cacheManager of class " + getClass().getName() 
				+ " can not be null");
		if (!cacheManager.cacheExists(cacheName)) {
			cacheManager.addCache(cacheName);
		}
		cache = cacheManager.getCache(cacheName);
	}

	/**
	 * Return the cached valuethat corresponds to key. 
	 * Note: If a DataRetrievalFailureException is found, it is thrown.
	 */
	private Object getCachedValue(
			final String cacheKey) {
		Element element = cache.get(cacheKey);
		if (element == null) {
			return null;
		}
		Object result = element.getObjectValue();
		if (result instanceof PortalGroupNotFoundException) {
			throw (PortalGroupNotFoundException) result;
		}
		return result;
	}

	//////////////////////////////////////////////////////////
	// user methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getUser(java.lang.String)
	 */
	@Override
	public PortalUser getUser(
			final String userId) {
		String cacheKey = "getUser " + userId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalUser) cached;
		}
		PortalUser result = super.getUser(userId);
		cache.put(new Element(cacheKey, result));
		return result;
	}

	//////////////////////////////////////////////////////////
	// group methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getGroupById(
	 * java.lang.String)
	 */
	@Override
	public PortalGroup getGroupById(
			final String groupId) {
		String cacheKey = "getGroupById " + groupId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroup) cached;
		}
		try {
			PortalGroup result = super.getGroupById(groupId);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getGroupByName(
	 * java.lang.String)
	 */
	@Override
	public PortalGroup getGroupByName(
			final String groupName) {
		String cacheKey = "getGroupByName " + groupName;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroup) cached;
		}
		try {
			PortalGroup result = super.getGroupByName(groupName);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getSubGroupsById(
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PortalGroup> getSubGroupsById(
			final String groupId) 
			throws PortalErrorException, PortalGroupNotFoundException {
		String cacheKey = "getSubGroupsById " + groupId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (List<PortalGroup>) cached;
		}
		try {
			List<PortalGroup> result = super.getSubGroupsById(groupId);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getSubGroupsByName(
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PortalGroup> getSubGroupsByName(
			final String groupName) 
			throws PortalErrorException, PortalGroupNotFoundException {
		String cacheKey = "getSubGroupsByName " + groupName;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (List<PortalGroup>) cached;
		}
		try {
			List<PortalGroup> result = super.getSubGroupsByName(groupName);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	//////////////////////////////////////////////////////////
	// group hierarchy methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getGroupHierarchyById(
	 * java.lang.String)
	 */
	@Override
	public PortalGroupHierarchy getGroupHierarchyById(
			final String groupId) 
	throws PortalErrorException, PortalGroupNotFoundException {
		String cacheKey = "getGroupHierarchyById " + groupId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroupHierarchy) cached;
		}
		try {
			PortalGroupHierarchy result = super.getGroupHierarchyById(groupId);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getGroupHierarchyByName(
	 * java.lang.String)
	 */
	@Override
	public PortalGroupHierarchy getGroupHierarchyByName(
			final String groupName) 
	throws PortalErrorException, PortalGroupNotFoundException {
		String cacheKey = "getGroupHierarchyByName " + groupName;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroupHierarchy) cached;
		}
		try {
			PortalGroupHierarchy result = super.getGroupHierarchyByName(groupName);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getRootGroup()
	 */
	@Override
	public PortalGroup getRootGroup() {
		String cacheKey = "getRootGroup ";
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroup) cached;
		}
		try {
			PortalGroup result = super.getRootGroup();
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getGroupHierarchy()
	 */
	@Override
	public PortalGroupHierarchy getGroupHierarchy() {
		String cacheKey = "getGroupHierarchy ";
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (PortalGroupHierarchy) cached;
		}
		try {
			PortalGroupHierarchy result = super.getGroupHierarchy();
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	//////////////////////////////////////////////////////////
	// group membership methods
	//////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#getUserGroups(
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PortalGroup> getUserGroups(
			final String userId) {
		String cacheKey = "getUserGroups " + userId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (List<PortalGroup>) cached;
		}
		try {
			List<PortalGroup> result = super.getUserGroups(userId);
			cache.put(new Element(cacheKey, result));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @see org.esupportail.portal.ws.client.support.uportal.BasicUportalServiceImpl#isUserMemberOfGroup(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isUserMemberOfGroup(
			final String userId, 
			final String groupId) {
		String cacheKey = "isUserMemberOfGroup " + userId + " " + groupId;
		Object cached = getCachedValue(cacheKey);
		if (cached != null) {
			return (Boolean) cached;
		}
		try {
			boolean result = super.isUserMemberOfGroup(userId, groupId);
			cache.put(new Element(cacheKey, new Boolean(result)));
			return result;
		} catch (PortalGroupNotFoundException e) {
			cache.put(new Element(cacheKey, e));
			throw e;
		}
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(final CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * @param cacheName the cacheName to set
	 */
	public void setCacheName(final String cacheName) {
		this.cacheName = cacheName;
	}

}
