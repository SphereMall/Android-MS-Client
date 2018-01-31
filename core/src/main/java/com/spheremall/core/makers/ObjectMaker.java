package com.spheremall.core.makers;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;

import java.util.Collections;
import java.util.List;

public class ObjectMaker<T extends Entity> implements Maker<T> {

    protected ResourceConverter resourceConverter;
    protected Class<T> clazz;

    public ObjectMaker(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Response<T> makeSingle(String response) {
        resourceConverter = new ResourceConverter(clazz);
        JSONAPIDocument<List<T>> document = resourceConverter.readDocumentCollection(response.getBytes(), clazz);
        T entity = clazz.cast(document.get().get(0));
        return new Response<>(entity, document.getMeta());
    }

    @Override
    public Response<List<T>> makeAsList(String response) {
        resourceConverter = new ResourceConverter(clazz);
        JSONAPIDocument<List<T>> document = resourceConverter.readDocumentCollection(response.getBytes(), clazz);
        List<T> entities = Collections.checkedList(resourceConverter.readDocumentCollection(response.getBytes(), clazz).get(), clazz);
        return new Response<>(entities, document.getMeta());
    }
}