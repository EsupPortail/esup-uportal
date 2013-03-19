package org.jasig.portal.groups.smartldap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import org.jasig.portal.groups.IEntityGroup;
import org.jasig.portal.groups.EntityTestingGroupImpl;
import org.jasig.portal.security.IPerson;

public class SimpleContextMapper implements ContextMapper{

  private static final String GROUP_DESCRIPTION = 
			"This group was pulled from the directory server.";

	/**
	 * Name of the LDAP attribute on a group that tells you 
	 * its key (normally 'dn').
	 */
	private String keyAttributeName = null; 

	/**
	 * Name of the LDAP attribute on a group that tells you 
	 * the name of the group.
	 */
	private String groupNameAttributeName = null; 

	/**
	 * Name of the LDAP attribute on a group that tells you 
	 * who its children are.
	 */
	private String membershipAttributeName = null; 

	private final Log log = LogFactory.getLog(getClass());

	/*
	 * Public API.
	 */	

	public Object mapFromContext(Object ctx) {
		DirContextAdapter context = (DirContextAdapter) ctx;
		
		// Assertions.
		if (groupNameAttributeName == null) {
			String msg = "The property 'groupNameAttributeName' must be set.";
			throw new IllegalStateException(msg);
		}
		if (membershipAttributeName == null) {
			String msg = "The property 'membershipAttributeName' must be set.";
			throw new IllegalStateException(msg);
		}

		if (log.isInfoEnabled()) {
			String msg = "SimpleContextMapper.mapFromContext() :: settings:  groupNameAttributeName='" 
					+ groupNameAttributeName + "', membershipAttributeName='" 
					+ membershipAttributeName + "'";
			log.info(msg);
		}

		LdapRecord rslt;

		try {
            
			String key ;
			if (keyAttributeName == null) {
			    key = (String) context.getDn().toString();
			} else {
			    key = (String) context.getStringAttribute(keyAttributeName);
			}
			String groupName = (String) context.getStringAttribute(groupNameAttributeName);
    
			IEntityGroup g = new EntityTestingGroupImpl(key, IPerson.class);
			g.setCreatorID("System");
			g.setName(groupName);
			g.setDescription(GROUP_DESCRIPTION);
			
			List<String> membership = new LinkedList<String>(Arrays.asList(context.getStringAttributes(membershipAttributeName)));
			
			rslt = new LdapRecord(g, membership);

			if (log.isInfoEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("Record Details:")
				.append("\n\tkey=").append(key)
				.append("\n\tgroupName=").append(groupName)
				.append("\n\tmembers:");
				for (String s : membership) {
					msg.append("\n\t\t").append(s);
				}
				log.debug(msg.toString());
			}

		} catch (Throwable t) {
			log.error("Error in SimpleContextMapper", t);
			String msg = "SimpleContextMapper failed to create a LdapRecord "
					+ "from the specified ctx:  " + ctx;
			throw new RuntimeException(msg, t);
		}

		return rslt;		

	}

	public void setKeyAttributeName(String keyAttributeName) {
		this.keyAttributeName = keyAttributeName;
	}

	public void setGroupNameAttributeName(String groupNameAttributeName) {
		this.groupNameAttributeName = groupNameAttributeName;
	}

	public void setMembershipAttributeName(String membershipAttributeName) {
		this.membershipAttributeName = membershipAttributeName;
	}

}
