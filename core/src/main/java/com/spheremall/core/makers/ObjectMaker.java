package com.spheremall.core.makers;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.jsonapi.DeserializationFeature;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMaker<T extends Entity> implements Maker<T> {

    protected ResourceConverter resourceConverter;
    protected Class<T> clazz;

    public ObjectMaker(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Response<T> makeSingle(String response) {
        resourceConverter = new ResourceConverter(clazz);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        JSONAPIDocument<List<T>> document = resourceConverter.readDocumentCollection(response.getBytes(), clazz);
        T entity = clazz.cast(document.get().get(0));
        Map<String, ?> meta = document.getMeta();
        if (meta == null) {
            meta = new HashMap<>();
        }
        return new Response<>(entity, meta);
    }

    @Override
    public Response<List<T>> makeAsList(String response) {
        resourceConverter = new ResourceConverter(clazz);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        JSONAPIDocument<List<T>> document = resourceConverter.readDocumentCollection(response.getBytes(), clazz);
        List<T> entities = Collections.checkedList(resourceConverter.readDocumentCollection(response.getBytes(), clazz).get(), clazz);
        Map<String, ?> meta = document.getMeta();
        if (meta == null) {
            meta = new HashMap<>();
        }
        return new Response<>(entities, meta);
    }
}