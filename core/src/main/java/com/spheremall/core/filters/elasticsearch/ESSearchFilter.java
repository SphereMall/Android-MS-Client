package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchFilter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.utils.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ESSearchFilter extends Filter implements ElasticSearchFilter {

    public ElasticSearchQuery searchQuery;
    private String index = "sm-*";

    @Override
    public ElasticSearchFilter index(String... indexes) {
        this.index = TextUtils.join(",", Arrays.asList(indexes));
        return this;
    }

    @Override
    public ElasticSearchFilter query(ElasticSearchQuery elasticSearchQuery) {
        searchQuery = elasticSearchQuery;
        return this;
    }

    @Override
    public String toString() {
        return toJson(searchQuery).toString();
    }

    private JSONObject toJson(ElasticSearchQuery searchQuery) {
        JSONObject queryJson = new JSONObject();
        try {
            queryJson.put("query", searchQuery.toJson());
            queryJson.put("index", this.index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return queryJson;
    }
}
