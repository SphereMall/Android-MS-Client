package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.api.response.ReceivedTypes;
import com.spheremall.core.entities.EntitiesProvider;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridMaker extends ObjectMaker<Entity> {

    public GridMaker(Class<Entity> clazz) {
        super(clazz);
    }

    @Override
    public Response<List<Entity>> makeAsList(String response) {
        Class[] classes = extractEntitiesClasses(response);
        resourceConverter = new ResourceConverter(classes);
        JSONAPIDocument<List<Entity>> document = resourceConverter.readDocumentCollection(response.getBytes(), clazz);
        return new Response<>(document.get(), document.getMeta());
    }

    private Class[] extractEntitiesClasses(String response) {
        Gson gson = new Gson();
        ReceivedTypes receivedTypes = gson.fromJson(response, ReceivedTypes.class);
        Set<Class> classes = new HashSet<>();
        for (int i = 0; i < receivedTypes.data.size(); i++) {
            classes.add(EntitiesProvider.availableEntities.get(receivedTypes.data.get(i).type));
        }
        for (int i = 0; i < receivedTypes.included.size(); i++) {
            classes.add(EntitiesProvider.availableEntities.get(receivedTypes.included.get(i).type));
        }
        return classes.toArray(new Class[classes.size()]);
    }
}
