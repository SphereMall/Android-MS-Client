package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ObjectMapper<OUT> {

    private ObjectMapperProvider mapperProvider = new ObjectMapperProvider();
    private Map<String, ObjectMapper> cachedMappers = new HashMap<>();

    protected Map<String, List<Entity>> getRelations(JsonObject relations, JsonArray includes) {
        Map<String, List<Entity>> relatedEntities = new HashMap<>();

        for (String key : relations.keySet()) {
            JsonObject relationObject = relations.getAsJsonObject(key);
            JsonArray relationKeyList = relationObject.getAsJsonArray("data");

            for (int j = 0; j < relationKeyList.size(); j++) {
                JsonObject relatedItem = relationKeyList.get(j).getAsJsonObject();

                for (int i = 0; i < includes.size(); i++) {
                    JsonObject include = includes.get(i).getAsJsonObject();
                    if (include.getAsJsonPrimitive("type").getAsString().equals(key)
                            && include.getAsJsonPrimitive("id").getAsString().equals(relatedItem.getAsJsonPrimitive("id").getAsString())) {

                        ObjectMapper mapper;
                        try {
                            if (cachedMappers.containsKey(key)) {
                                mapper = cachedMappers.get(key);
                            } else {
                                mapper = mapperProvider.provide(key);
                                cachedMappers.put(key, mapper);
                            }

                            Entity entity = (Entity) mapper.doObject(include.getAsJsonObject("attributes"), null, null);

                            if (relatedEntities.containsKey(key)) {
                                relatedEntities.get(key).add(entity);
                            } else {
                                relatedEntities.put(key, new ArrayList<>());
                                relatedEntities.get(key).add(entity);
                            }

                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
        return relatedEntities;
    }

    public abstract OUT doObject(JsonObject obj, JsonObject relations, JsonArray includes) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
}
