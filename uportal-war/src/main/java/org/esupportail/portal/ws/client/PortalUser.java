package org.esupportail.portal.ws.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


/** 
 * A basic portal user, with an id and attributes.
 */
public final class PortalUser {
	
	/**
	 * The id of the user.
	 */
	private String id;
	
	/**
	 * The attributes.
	 */
	private Map<String, List<String>> attributes;
	
	/**
	 * Constructor.
	 * @param id
	 * @param attributes 
	 */
	public PortalUser(
			final String id,
			final Map<String, List<String>> attributes) {
		super();
		this.attributes = Collections.unmodifiableMap(attributes);
		this.id = id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() 
		+ "[id=[" + id + "], attributes=" + attributes.toString() + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, List<String>> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributeName 
	 * @return the values for the attribute attributeName.
	 */
	public List<String> getAttributeValues(
			final String attributeName) {
		return attributes.get(attributeName);
	}

	/**
	 * @return the names of the attributes.
	 */
	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

}
