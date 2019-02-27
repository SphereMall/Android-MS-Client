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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CatalogDataMaker extends ObjectMaker<Entity> {

    private JsonArray dataObject = new JsonArray();
    private JsonParser parser = new JsonParser();

    private Map<TypeIdRelation, JsonObject> includes = new HashMap<>();

    public CatalogDataMaker(Class<Entity> clazz) {
        super(clazz);
    }

    public void add(String single) {
        JsonObject jsonObject = (JsonObject) parser.parse(single);
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        dataObject.add(jsonArray.get(0));
        JsonArray includes = jsonObject.getAsJsonArray("included");

        for (int i = 0; i < includes.size(); i++) {
            JsonObject object = includes.get(i).getAsJsonObject();
            if (object.has("id") && object.has("type")) {
                int id = object.getAsJsonPrimitive("id").getAsInt();
                String type = object.getAsJsonPrimitive("type").getAsString();
                if (!this.includes.containsKey(new TypeIdRelation(id, type))) {
                    this.includes.put(new TypeIdRelation(id, type), object);
                }
            }
        }
    }

    @Override
    public Response<List<Entity>> makeAsList(String response) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", dataObject);
        JsonArray includesObject = new JsonArray();
        for (Map.Entry<TypeIdRelation, JsonObject> entry : includes.entrySet()) {
            includesObject.add(entry.getValue());
        }
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
