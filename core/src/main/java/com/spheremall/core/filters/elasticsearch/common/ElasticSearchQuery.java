package com.spheremall.core.filters.elasticsearch.common;

import org.json.JSONObject;

public abstract class ElasticSearchQuery {
    protected String name;

    public abstract JSONObject toJson();
}
