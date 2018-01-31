package com.spheremall.core.utils;

import java.util.Iterator;

public class TextUtils {

    public static String join(CharSequence delimiter, Iterable tokens) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = tokens.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            stringBuilder.append(o);
            if (iterator.hasNext()) {
                stringBuilder.append(delimiter);
            }
        }
        return stringBuilder.toString();
    }
}
