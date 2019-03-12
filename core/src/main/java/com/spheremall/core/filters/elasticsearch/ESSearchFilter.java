package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchFilter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;
import com.spheremall.core.utils.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ESSearchFilter extends Filter implements ElasticSearchFilter {

    public ElasticSearchQuery searchQuery;
    private String index = "sm-*";
    private ArrayList<String> sourceFields = new ArrayList<>();
    private SortFilter sortFilter = null;
    private int size = 0;
    private int from = 0;

    @Override
    public String indexes() {
        return index;
    }

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
    public ElasticSearchFilter sort(SortFilter sortFilter) {
        this.sortFilter = sortFilter;
        return this;
    }

    @Override
    public ElasticSearchFilter source(String... fields) {
        sourceFields.addAll(Arrays.asList(fields));
        return this;
    }

    @Override
    public ElasticSearchFilter setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public ElasticSearchFilter setFrom(int from) {
        this.from = from;
        return this;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    public JSONObject toJson() {
        JSONObject queryJson = new JSONObject();
        try {
            queryJson.put("query", searchQuery.toJson());
            queryJson.put("index", this.index);

            if (sourceFields.size() > 0) {
                queryJson.put("_source", TextUtils.join(",", sourceFields));
            }

            if (this.size > 0) queryJson.put("size", this.size);
            if (this.from > 0) queryJson.put("from", this.from);

            if (sortFilter != null) {
                queryJson.put(SortFilter.SORT_KEY, sortFilter.toJson().getJSONArray(SortFilter.SORT_KEY));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return queryJson;
    }

    @Override
    public Filter asFilter() {
        return this;
    }
}
