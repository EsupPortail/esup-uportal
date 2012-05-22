/**
 * ESUP-Portail Commons - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-commons
 */
package org.esupportail.portal.ws.client.support.uportal;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.portal.ws.client.PortalGroup;
import org.esupportail.portal.ws.client.PortalGroupHierarchy;
import org.esupportail.portal.ws.client.TestablePortalService;
import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.esupportail.portal.ws.client.exceptions.PortalUserNotFoundException;
import org.springframework.util.StringUtils;

/** 
 * An implementation of PortalService that relies on a uPortal web service, as proposed by
 * the package esuup-portal-ws.
 */
public class TestableUportalServiceImpl extends BasicUportalServiceImpl implements TestablePortalService {

	/**
	 * Logger object.
	 */
	private final Log logger = LogFactory.getLog(TestableUportalServiceImpl.class);
	
	/**
	 * The test user id.
	 */
	private String testUserId;

	/**
	 * The test group id.
	 */
	private String testGroupId;

	/**
	 * The test group name.
	 */
	private String testGroupName;

	/**
	 * The test group token.
	 */
	private String testGroupToken;

	/**
	 * Bean constructor.
	 */
	public TestableUportalServiceImpl() {
		super();
	}
	
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		if (!StringUtils.hasText(testUserId)) {
			testUserId = null;
			logger.warn(getClass() 
					+ ": property testUserId is not set, all the tests will not be available");
		}
		if (!StringUtils.hasText(testGroupId)) {
			testGroupId = null;
			logger.warn(getClass() 
					+ ": property testGroupId is not set, all the tests will not be available");
		}
		if (!StringUtils.hasText(testGroupName)) {
			testGroupName = null;
			logger.warn(getClass() 
					+ ": property testGroupName is not set, all the tests will not be available");
		}
	}

	/**
	 * Log a group hierarchy.
	 * @param indentLevel 
	 * @param hierarchy
	 */
	private void logGroupHierarchy(
			final PortalGroupHierarchy hierarchy,
			final int indentLevel) {
		String prefix = "";
		for (int i = 0; i < indentLevel; i++) {
			prefix += "---";
		}
		logger.info(prefix + hierarchy.getGroup().getId() + "/" + hierarchy.getGroup().getName());
		if (hierarchy.getSubHierarchies() != null) {
			for (PortalGroupHierarchy subHierarchy : hierarchy.getSubHierarchies()) {
				logGroupHierarchy(subHierarchy, indentLevel + 1);
			}
		}
	}

	/**
	 * Log a group hierarchy.
	 * @param hierarchy
	 */
	private void logGroupHierarchy(
			final PortalGroupHierarchy hierarchy) {
		logGroupHierarchy(hierarchy, 0);
	}

	/**
	 * @see org.esupportail.portal.ws.client.TestablePortalService#test()
	 */
	public void test() {
		if (testUserId == null) {
			logger.error("property testUserId is null, " 
					+ "can not test methods getUser() and getUserAttributes()");
		} else {
			logger.info("looking for user [" + testUserId + "]...");
			try {
				logger.info(getUser(testUserId).toString());
			} catch (PortalUserNotFoundException e) {
				logger.error(e.getMessage());
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getClass() + " -> " + e.getMessage());
			}
		}
		if (testGroupId == null) {
			logger.error("property testGroupId is null, " 
					+ "can not test methods getGroupById(), " 
					+ "getSubGroupsById() and getGroupHierarchyById()");
		} else {
			logger.info("looking for group with id [" + testGroupId + "]...");
			try {
				logger.info(getGroupById(testGroupId).toString());
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
			logger.info("looking for the sub groups of the group with id [" + testGroupId + "]...");
			try {
				for (PortalGroup group : getSubGroupsById(testGroupId)) {
					logger.info("- " + group.toString());
				}
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
			logger.info("extracting the group hierarchy of the group with id [" + testGroupId + "]...");
			try {
				logGroupHierarchy(getGroupHierarchyById(testGroupId));
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		if (testGroupName == null) {
			logger.error("property testGroupName is null, " 
					+ "can not test methods getGroupByName(), " 
					+ " getSubGroupsByName() and getGroupHierarchyByName()");
		} else {
			logger.info("looking for group with name [" + testGroupName + "]...");
			try {
				logger.info(getGroupByName(testGroupName).toString());
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
			logger.info("looking for the sub groups of the group with name [" + testGroupName + "]...");
			try {
				List<PortalGroup> groups = getSubGroupsByName(testGroupName);
				if (groups.isEmpty()) {
					logger.info("none");
				} else {
					for (PortalGroup group : groups) {
						logger.info("- " + group.toString());
					}
				}
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
			logger.info("extracting the group hierarchy of the group with name [" + testGroupName + "]...");
			try {
				logGroupHierarchy(getGroupHierarchyByName(testGroupName));
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		if (testUserId == null) {
			logger.error("property testUserId is null, can not test method isUserMemberOfGroup()");
		} else if (testGroupId == null) {
			logger.error("property testGroupId is null, can not test method isUserMemberOfGroup()");
		} else {
			logger.info("is user [" + testUserId + "] member of group [" + testGroupId + "]?");
			try {
				logger.info(Boolean.toString(isUserMemberOfGroup(testUserId, testGroupId)));
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		if (testGroupToken == null) {
			logger.error("property testGroupToken is null, can not test method searchGroupByName()");
		} else {
			logger.info("looking for the groups of which name contains [" + testGroupToken + "]?");
			try {
				List<PortalGroup> groups = searchGroupsByName(testGroupToken);
				if (groups.isEmpty()) {
					logger.info("none");
				} else {
					for (PortalGroup group : groups) {
						logger.info("- " + group.toString());
					}
				}
				logger.info(Boolean.toString(isUserMemberOfGroup(testUserId, testGroupId)));
			} catch (PortalErrorException e) {
				logger.error(e.getMessage());
			} catch (PortalGroupNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		logger.info("looking for the root group...");
		try {
			logger.info(getRootGroup().toString());
		} catch (PortalErrorException e) {
			logger.error(e.getMessage());
		} catch (PortalGroupNotFoundException e) {
			logger.error(e.getMessage());
		}
		logger.info("extracting the group hierarchy...");
		try {
			logGroupHierarchy(getGroupHierarchy());
		} catch (PortalErrorException e) {
			logger.error(e.getMessage());
		} catch (PortalGroupNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @param testGroupId the testGroupId to set
	 */
	public void setTestGroupId(final String testGroupId) {
		this.testGroupId = testGroupId;
	}

	/**
	 * @param testGroupName the testGroupName to set
	 */
	public void setTestGroupName(final String testGroupName) {
		this.testGroupName = testGroupName;
	}

	/**
	 * @param testUserId the testUserId to set
	 */
	public void setTestUserId(final String testUserId) {
		this.testUserId = testUserId;
	}

	/**
	 * @param testGroupToken the testGroupToken to set
	 */
	public void setTestGroupToken(final String testGroupToken) {
		this.testGroupToken = testGroupToken;
	}

	/**
	 * The main method.
	 * @param args
	 */
	public static void main(final String[] args) {
		TestableUportalServiceImpl portalService = new TestableUportalServiceImpl();
		portalService.setUrl("http://localhost:8080/services/UportalService");
		portalService.setTestUserId("paubryx");
		portalService.setTestGroupId("local.0");
		portalService.setTestGroupName("Tous les groupes de personnes");
		portalService.setTestGroupToken("per");
		portalService.test();
	}

}
