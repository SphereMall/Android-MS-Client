package com.spheremall.core.entities;

import java.util.Map;

public class Response<T> {

    private Map<String, ?> meta;
    private T data;

    public Response(T data, Map<String, ?> meta) {
        this.data = data;
        this.meta = meta;
    }

    public Map<String, ?> meta() {
        return meta;
    }

    public T data() {
        return data;
    }
}
