package com.spheremall.core.utils;

public class CorrelationTypeHelper {

    public static String getGraphTypeByClass(Class clazz) {
        return clazz.getSimpleName().toLowerCase() + "s";
    }
}
