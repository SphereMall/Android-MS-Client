package com.spheremall.core.utils;

public class ElasticSearchIndexHelper {

    public static String getIndexByClass(Class className) {
        String type = className.getSimpleName().toLowerCase();
        return "sm-" + type + "s";
    }
}
