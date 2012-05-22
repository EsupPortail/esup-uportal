package org.esupportail.portal.ws.server;

import java.util.List;

import org.jasig.portal.groups.IEntityGroup;

/** 
 * A basic portal group hierarchy.
 */
class UportalGroupHierarchy {
	
	/**
	 * The group.
	 */
	private IEntityGroup group;
	
	/**
	 * The members' group hierarchies, null if no child.
	 */
	private List<UportalGroupHierarchy> subHierarchies;
	
	/**
	 * Constructor.
	 * @param group 
	 * @param subHierarchies 
	 */
	public UportalGroupHierarchy(
			final IEntityGroup group, 
			final List<UportalGroupHierarchy> subHierarchies) {
		super();
		this.group = group;
		this.subHierarchies = subHierarchies;
	}

	/**
	 * Convert the object into an array of objects.
	 * @return a 2-array that represents the group hierarchy:
	 * 0: a 2-array object (0: the group id, 1: the group name).
	 * 1: an array of group hierarchies (null if no sub group).
	 */
	public Object[] toArray() {
		Object[] result = new Object[2];
		Object[] groupArray = new Object[2];
		groupArray[0] = group.getKey();
		groupArray[1] = group.getName();
		result[0] = groupArray;
		if (subHierarchies == null) {
			result[1] = null;
		} else {
			Object [] hierarchyArray = new Object[subHierarchies.size()];
			for (int i = 0; i < subHierarchies.size(); i++) {
				hierarchyArray[i] = subHierarchies.get(i).toArray();
			}
			result[1] = hierarchyArray;
		}
		return result;
	}

}
