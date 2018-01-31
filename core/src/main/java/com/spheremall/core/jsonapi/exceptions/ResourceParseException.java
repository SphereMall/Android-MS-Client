package com.spheremall.core.jsonapi.exceptions;

import com.spheremall.core.jsonapi.errors.Errors;

/**
 * ResourceParseException implementation. <br />
 * This exception is thrown from ResourceConverter in case parsed body contains 'errors' node.
 *
 * @author jbegic
 */
public class ResourceParseException extends RuntimeException {
	private final Errors errors;

	public ResourceParseException(Errors errors) {
		super(errors.toString());
		this.errors = errors;
	}

	/**
	 * Returns Errors or <code>null</code>
	 * @return {@link Errors}
	 */
	public Errors getErrors() {
		return errors;
	}
}
