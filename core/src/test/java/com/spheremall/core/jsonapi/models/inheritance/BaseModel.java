package com.spheremall.core.jsonapi.models.inheritance;

import com.spheremall.core.jsonapi.annotations.Id;

/**
 * Base resource class.
 *
 * @author jbegic
 */
public class BaseModel {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
