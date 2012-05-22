package org.esupportail.portal.ws.client;


import java.util.List;

/** 
 * A basic portal group hierarchy.
 */
public final class PortalGroupHierarchy {
	
	/**
	 * The group.
	 */
	private PortalGroup group;
	
	/**
	 * The list of the members' group hierarchies, null if no child.
	 */
	private List<PortalGroupHierarchy> subHierarchies;
	
	/**
	 * Constructor.
	 * @param group 
	 * @param subHierarchies 
	 */
	public PortalGroupHierarchy(
			final PortalGroup group, 
			final List<PortalGroupHierarchy> subHierarchies) {
		super();
		this.group = group;
		this.subHierarchies = subHierarchies;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName() + "#" + hashCode() + "[group=" + group + ", subHierarchies=");
		if (subHierarchies == null) {
			sb.append("null");
		} else {
			sb.append("{");
			String separator = "";
			for (PortalGroupHierarchy hierarchy : subHierarchies) {
				sb.append(separator);
				sb.append(hierarchy.toString());
				separator = ", ";
			}
			sb.append("}");
		}
		sb.append("]");			
		return sb.toString();
	}

	/**
	 * @return the group
	 */
	public PortalGroup getGroup() {
		return group;
	}

	/**
	 * @return the subHierarchies
	 */
	public List<PortalGroupHierarchy> getSubHierarchies() {
		return subHierarchies;
	}

}
