package com.spheremall.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertyUtils {

    public static Boolean objectHasProperty(Object obj, String propertyName) {
        List<Field> properties = getAllFields(obj);
        for (Field field : properties) {
            if (field.getName().equalsIgnoreCase(propertyName)) {
                return true;
            }
        }
        return false;
    }

    public static String getValueByField(Object obj, String propertyName) {
        if (objectHasProperty(obj, propertyName)) {
            List<Field> properties = getAllFields(obj);
            for (Field field : properties) {
                if (field.getName().equalsIgnoreCase(propertyName)) {
                    try {
                        field.setAccessible(true);
                        return String.valueOf(field.get(obj));
                    } catch (IllegalAccessException e) {
                        return "";
                    }
                }
            }
        }
        return "";
    }

    private static List<Field> getAllFields(Object obj) {
        List<Field> fields = new ArrayList<>();
        getAllFieldsRecursive(fields, obj.getClass());
        return fields;
    }

    private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
        Collections.addAll(fields, type.getDeclaredFields());
        if (type.getSuperclass() != null) {
            fields = getAllFieldsRecursive(fields, type.getSuperclass());
        }
        return fields;
    }
}
