package com.spheremall.core.jsonapi.errors;

import java.util.List;

/**
 * JSON API error response.
 *
 * @author jbegic
 */
public class Errors {
	private List<Error> errors;

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Errors{" +
				"errors=" + (errors != null ? errors : "Undefined") +
				'}';
	}
}
