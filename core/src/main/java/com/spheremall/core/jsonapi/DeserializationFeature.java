package com.spheremall.core.jsonapi;

import java.util.HashSet;
import java.util.Set;

/**
 * Enumeration that defines list of deserialization features that can be set to {@link ResourceConverter}.
 *
 * @author jbegic
 */
public enum DeserializationFeature {

	/**
	 * This option enforces presence of the 'id' field in resources being parsed.
	 */
	REQUIRE_RESOURCE_ID(true),

	/**
	 * This option determines whether encountering unknown types results in {@link IllegalArgumentException} being
	 * thrown, or if parsing continues and the unknown field is ignored.
	 */
	ALLOW_UNKNOWN_INCLUSIONS(false);

	private final boolean enabledByDefault;

	DeserializationFeature(boolean enabledByDefault) {
		this.enabledByDefault = enabledByDefault;
	}

	/**
	 * Returns set of features that are enabled by default.
	 * @return returns features that are enabled by default
	 */
	public static Set<DeserializationFeature> getDefaultFeatures() {
		Set<DeserializationFeature> result = new HashSet<>();

		for (DeserializationFeature deserializationFeature : values()) {
			if (deserializationFeature.enabledByDefault) {
				result.add(deserializationFeature);
			}
		}

		return result;
	}
}
