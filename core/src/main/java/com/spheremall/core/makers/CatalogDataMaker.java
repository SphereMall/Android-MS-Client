package com.spheremall.core.makers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.mappers.entityMappers.JsonObjectUtils;
import com.spheremall.core.mappers.entityMappers.ObjectMapper;
import com.spheremall.core.mappers.entityMappers.ObjectMapperProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CatalogDataMaker extends ObjectMaker<Entity> {

    private JsonArray dataObject = new JsonArray();
    private JsonParser parser = new JsonParser();

    private Map<TypeIdRelation, JsonObject> includes = new HashMap<>();

    protected ExecutorService executors = Executors.newFixedThreadPool(50);

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

    Map<String, ObjectMapper> cachedMappers = new HashMap<>();
    List<Entity> entities = new ArrayList<>();

    @Override
    public Response<List<Entity>> makeAsList(String response) {

        JsonArray includesArray = new JsonArray();
        for (Map.Entry<TypeIdRelation, JsonObject> entry : includes.entrySet()) {
            includesArray.add(entry.getValue());
        }
        System.out.println("Execute start");
        for (int i = 0; i < dataObject.size(); i++) {

//            JsonObject item = dataObject.get(i).getAsJsonObject();
//            ObjectMapperProvider provider = new ObjectMapperProvider();
//            try {
//                ObjectMapper mapper;
//                if (cachedMappers.containsKey(item.getAsJsonPrimitive("type").getAsString())) {
//                    mapper = cachedMappers.get(item.getAsJsonPrimitive("type").getAsString());
//                } else {
//                    mapper = provider.provide(item.getAsJsonPrimitive("type").getAsString());
//                    cachedMappers.put(item.getAsJsonPrimitive("type").getAsString(), mapper);
//                }
//
//                JsonObject relations = JsonObjectUtils.getObject(item, "relationships");
//
//                Entity entity = (Entity) mapper.doObject(
//                        item.getAsJsonObject("attributes"),
//                        relations,
//                        includesArray);
//
//                entities.add(entity);
//                System.out.println(i);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }

            int finalI = i;
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    JsonObject item = dataObject.get(finalI).getAsJsonObject();
                    ObjectMapperProvider provider = new ObjectMapperProvider();
                    try {
                        ObjectMapper mapper;
                        if (cachedMappers.containsKey(item.getAsJsonPrimitive("type").getAsString())) {
                            mapper = cachedMappers.get(item.getAsJsonPrimitive("type").getAsString());
                        } else {
                            mapper = provider.provide(item.getAsJsonPrimitive("type").getAsString());
                            cachedMappers.put(item.getAsJsonPrimitive("type").getAsString(), mapper);
                        }

                        JsonObject relations = JsonObjectUtils.getObject(item, "relationships");

                        Entity entity = (Entity) mapper.doObject(
                                item.getAsJsonObject("attributes"),
                                relations,
                                includesArray);

                        entities.add(entity);
                        System.out.println(finalI);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            });
//            executors.execute(new WorkerThread(() -> {
//
//            }, i, includesArray));
        }

        System.out.println("Test: " + entities.size());
        return new Response<>(new ArrayList<>(), new HashMap<>());
    }

    public class WorkerThread extends Thread {

        private final Callback callback;
        private final int i;
        private final JsonArray includesArray;

        public WorkerThread(Callback callback, int i, JsonArray includesArray) {
            this.callback = callback;
            this.i = i;
            this.includesArray = includesArray;
        }

        @Override
        public void run() {
            JsonObject item = dataObject.get(i).getAsJsonObject();
            ObjectMapperProvider provider = new ObjectMapperProvider();
            try {
                ObjectMapper mapper;
                if (cachedMappers.containsKey(item.getAsJsonPrimitive("type").getAsString())) {
                    mapper = cachedMappers.get(item.getAsJsonPrimitive("type").getAsString());
                } else {
                    mapper = provider.provide(item.getAsJsonPrimitive("type").getAsString());
                    cachedMappers.put(item.getAsJsonPrimitive("type").getAsString(), mapper);
                }

                JsonObject relations = JsonObjectUtils.getObject(item, "relationships");

                Entity entity = (Entity) mapper.doObject(
                        item.getAsJsonObject("attributes"),
                        relations,
                        includesArray);

                entities.add(entity);
                System.out.println(i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    interface Callback {
        void invoke();
    }
}
