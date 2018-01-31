package com.spheremall.core.jsonapi.models;

import com.spheremall.core.jsonapi.LongIdHandler;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

/**
 * Model class used to test {@link Long} as resource identifier.
 *
 * @author jbegic
 */
@Type("long-id-type")
public class LongIdResource {
	
	@Id(LongIdHandler.class)
	private Long id;
	
	private String value;
	
	@Relationship("integer-id-relationship")
	private IntegerIdResource integerIdResource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public IntegerIdResource getIntegerIdResource() {
		return integerIdResource;
	}

	public void setIntegerIdResource(IntegerIdResource integerIdResource) {
		this.integerIdResource = integerIdResource;
	}
}
