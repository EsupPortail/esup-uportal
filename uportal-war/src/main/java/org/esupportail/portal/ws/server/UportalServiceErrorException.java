package org.esupportail.portal.ws.server;

/** 
 * The exception to wrap uPortral exceptions.
 */
public class UportalServiceErrorException extends RuntimeException {

	/**
	 * The id for serialization.
	 */
	private static final long serialVersionUID = 8123244696712420458L;

	/**
	 * Constructor.
	 * @param exception
	 */
	private UportalServiceErrorException(
			final String message, 
			final Exception exception) {
		super(message, exception);
	}

	/**
	 * @param exception 
	 * @return an exception, to wrap uPortal exceptions.
	 */
	public static UportalServiceErrorException error(
			final Exception exception) {
		String message = exception.getClass().getSimpleName();
		if (exception.getMessage() != null) {
			message = message + ": " + exception.getMessage();
		}
		return new UportalServiceErrorException(message, exception);
	}

}
