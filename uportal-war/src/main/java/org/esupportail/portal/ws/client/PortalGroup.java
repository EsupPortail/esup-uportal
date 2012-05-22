package org.esupportail.portal.ws.client;


/** 
 * A basic portal group, with an id and a name.
 */
public final class PortalGroup {
	
	/**
	 * The id of the group.
	 */
	private String id;
	
	/**
	 * The name of the group.
	 */
	private String name;
	
	/**
	 * Constructor.
	 * @param id
	 * @param name
	 */
	public PortalGroup(
			final String id, 
			final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() + "[id=[" + id + "], name=[" + name + "]]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
