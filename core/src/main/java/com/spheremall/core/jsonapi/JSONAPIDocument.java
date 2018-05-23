package com.spheremall.core.jsonapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spheremall.core.jsonapi.errors.Error;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON API Document wrapper.
 *
 * @param <T> the type parameter
 * @author jbegic
 */
public class JSONAPIDocument<T> {
    private T data;
    private ObjectMapper deserializer;

    private Iterable<? extends Error> errors;

    /**
     * Top level response link object.
     */
    private Links links;

    /**
     * A map of meta fields, keyed by the meta field name
     */
    private Map<String, ?> meta;


    /**
     * Creates new JsonApiDocument.
     *
     * @param data {@link T} API resource type
     */
    public JSONAPIDocument(T data) {
        this.data = data;
    }

    /**
     * Creates new JSONAPIDocument.
     *
     * @param data         {@link T} API resource type
     * @param deserializer {@link ObjectMapper} deserializer to be used for handling meta conversion
     */
    public JSONAPIDocument(T data, ObjectMapper deserializer) {
        this(data);
        this.deserializer = deserializer;
    }

    /**
     * Creates new JSONAPIDocument.
     */
    public JSONAPIDocument() {
        // Default constructor
    }

    /**
     * Factory method for creating JSONAPIDocument that holds the Error object.
     * <p>
     * <p>
     * This method should be used in case error response is being built by the server side.
     * </p>
     *
     * @param errors
     */
    public static JSONAPIDocument<?> createErrorDocument(Iterable<? extends Error> errors) {
        JSONAPIDocument<?> result = new JSONAPIDocument();
        result.errors = errors;
        return result;
    }

    /**
     * Gets resource object
     *
     * @return {@link T} resource object
     */
    public T get() {
        return data;
    }

    /**
     * Get meta data.
     *
     * @return {@link Map} meta
     */
    public Map<String, ?> getMeta() {
        return meta;
    }

    /**
     * Sets meta data.
     *
     * @param meta {@link Map} meta
     */
    public void setMeta(Map<String, ?> meta) {
        if (meta == null) {
            meta = new HashMap<>();
        }
        this.meta = new HashMap<>(meta);
    }

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Returns typed meta-data object or <code>null</code> if no meta is present.
     *
     * @param metaType {@link Class} target type
     * @param <T>      type
     * @return meta or <code>null</code>
     */
    public <T> T getMeta(Class<?> metaType) {
        if (meta != null && deserializer != null) {
            return (T) deserializer.convertValue(meta, metaType);
        }

        return null;
    }

    /**
     * Returns error objects or <code>null</code> in case no errors were set.
     *
     * @return {@link Iterable} errors
     */
    public Iterable<? extends Error> getErrors() {
        return errors;
    }
}
