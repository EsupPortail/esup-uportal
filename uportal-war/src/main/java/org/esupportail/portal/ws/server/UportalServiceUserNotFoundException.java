package org.esupportail.portal.ws.server;

/** 
 * The exception thrown when a user is not found in uPortal.
 */
public class UportalServiceUserNotFoundException extends RuntimeException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = -1231089783668050472L;

	/**
	 * Constructor.
	 * @param message
	 * @param exception
	 */
	private UportalServiceUserNotFoundException(
			final String message, 
			final Exception exception) {
		super(message, exception);
	}

	/**
	 * @param userId 
	 * @param exception 
	 * @return an exception, to throw when a user id is not found.
	 */
	public static UportalServiceUserNotFoundException userIdNotFound(
			final String userId, 
			final Exception exception) {
		return new UportalServiceUserNotFoundException("user with id [" + userId + "] not found", exception);
	}

	/**
	 * @param userId 
	 * @return an exception, to throw when a user id is not found.
	 */
	public static UportalServiceUserNotFoundException userIdNotFound(
			final String userId) {
		return userIdNotFound(userId, null);
	}

}
