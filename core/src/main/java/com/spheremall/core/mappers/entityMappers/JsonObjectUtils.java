package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class JsonObjectUtils {

    public static String getString(JsonObject object, String key) {
        if (!object.has(key)) return "";

        if (object.get(key) == null || object.get(key) instanceof JsonNull) return "";

        return object.getAsJsonPrimitive(key).getAsString();
    }

    public static JsonObject getObject(JsonObject object, String key) {
        if (!object.has(key)) return null;

        if (object.get(key) == null || object.get(key) instanceof JsonNull) return null;

        if (!(object.get(key) instanceof JsonObject)) return null;

        return object.getAsJsonObject(key);
    }

    public static Double getDouble(JsonObject object, String key) {
        if (!object.has(key)) return 0D;

        if (object.get(key) == null || object.get(key) instanceof JsonNull) return 0D;

        return object.getAsJsonPrimitive(key).getAsDouble();
    }

    public static Integer getInt(JsonObject object, String key) {
        if (!object.has(key)) return 0;

        if (object.get(key) == null || object.get(key) instanceof JsonNull) return 0;

        return object.getAsJsonPrimitive(key).getAsInt();
    }
}
