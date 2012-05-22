package org.esupportail.portal.ws.server;

/** 
 * The exception thrown when a group is not found in uPortal.
 */
public class UportalServiceGroupNotFoundException extends RuntimeException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = -8089885002337687383L;

	/**
	 * Constructor.
	 * @param message
	 * @param exception
	 */
	private UportalServiceGroupNotFoundException(
			final String message, 
			final Exception exception) {
		super(message, exception);
	}

	/**
	 * @param groupId 
	 * @param exception 
	 * @return an exception, to throw when a group id is not found.
	 */
	public static UportalServiceGroupNotFoundException groupIdNotFound(
			final String groupId, 
			final Exception exception) {
		return new UportalServiceGroupNotFoundException(
		"group with id [" + groupId + "] not found", exception);
	}

	/**
	 * @param groupId 
	 * @return an exception, to throw when a group id is not found.
	 */
	public static UportalServiceGroupNotFoundException groupIdNotFound(
			final String groupId) {
		return groupIdNotFound(groupId, null);
	}

	/**
	 * @param groupName 
	 * @param exception 
	 * @return an exception, to throw when a group name is not found.
	 */
	public static UportalServiceGroupNotFoundException groupNameNotFound(
			final String groupName, 
			final Exception exception) {
		return new UportalServiceGroupNotFoundException(
				"group with name [" + groupName + "] not found", exception);
	}

	/**
	 * @param groupName 
	 * @return an exception, to throw when a group name is not found.
	 */
	public static UportalServiceGroupNotFoundException groupNameNotFound(
			final String groupName) {
		return groupNameNotFound(groupName, null);
	}

}
