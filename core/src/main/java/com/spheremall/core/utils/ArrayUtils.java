package com.spheremall.core.utils;

import java.util.List;

public class ArrayUtils<T extends Number> {

    public String[] numericArrayToStringArray(T[] array) {

        String[] stringIds = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            stringIds[i] = String.valueOf(array[i]);
        }
        return stringIds;
    }

    public String[] numericListToStringArray(List<T> list) {
        String[] stringIds = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stringIds[i] = String.valueOf(list.get(i));
        }
        return stringIds;
    }
}
