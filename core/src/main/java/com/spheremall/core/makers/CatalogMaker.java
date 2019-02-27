package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spheremall.core.api.response.ReceivedTypes;
import com.spheremall.core.entities.EntitiesProvider;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;
import com.spheremall.core.makers.ObjectMaker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogMaker extends ObjectMaker<Entity> {

    private JsonArray dataObject = new JsonArray();
    private JsonArray includesObject = new JsonArray();
    private JsonParser parser = new JsonParser();

    public CatalogMaker(Class<Entity> clazz) {
        super(clazz);
    }

    public void add(String single) {
        JsonObject jsonObject = (JsonObject) parser.parse(single);
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        dataObject.add(jsonArray.get(0));
        JsonArray includes = jsonObject.getAsJsonArray("included");
        includesObject.addAll(includes);
    }

    @Override
    public Response<List<Entity>> makeAsList(String response) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", dataObject);
        jsonObject.add("included", includesObject);

        String res = jsonObject.toString();
        Class[] classes = extractEntitiesClasses(res);
        resourceConverter = new ResourceConverter(classes);
        byte[] bytes = res.getBytes();
        JSONAPIDocument<List<Entity>> document = resourceConverter.readDocumentCollection(bytes, clazz);
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
            if (EntitiesProvider.availableEntities.get(receivedTypes.included.get(i).type) != null) {
                classes.add(EntitiesProvider.availableEntities.get(receivedTypes.included.get(i).type));
            }
        }
        return classes.toArray(new Class[0]);
    }
}
