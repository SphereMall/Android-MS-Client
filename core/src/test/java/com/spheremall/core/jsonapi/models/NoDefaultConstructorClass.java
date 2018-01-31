package com.spheremall.core.jsonapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Type;

/**
 * Model used for testing object instantiation without default constructor present.
 *
 * @author jbegic
 */
@Type("no-def-ctor")
public class NoDefaultConstructorClass {

	@Id
	private String id;

	private String name;

	@JsonCreator
	public NoDefaultConstructorClass(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
