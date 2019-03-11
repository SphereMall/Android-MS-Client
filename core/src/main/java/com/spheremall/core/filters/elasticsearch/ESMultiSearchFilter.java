package com.spheremall.core.filters.elasticsearch;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.specifications.base.FilterSpecification;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ESMultiSearchFilter extends Filter implements FilterSpecification {

    public final List<ESSearchFilter> filters = new ArrayList<>();

    public ESMultiSearchFilter addFilter(ESSearchFilter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public String toString() {
        return asBody();
    }

    public String asBody() {
        StringBuilder body = new StringBuilder();
        for (ESSearchFilter filter : filters) {
            JsonObject indexObject = new JsonObject();
            String[] indexes = filter.indexes().split(",");
            JsonArray indexesArray = new JsonArray();

            for (String index : indexes) indexesArray.add(index);
            indexObject.add("index", indexesArray);

            JSONObject queryObject = filter.toJson();
            queryObject.remove("index");

            body.append(indexObject.toString()).append("\n");
            body.append(queryObject.toString()).append("\n");
        }
        return body.toString();
    }

    @Override
    public Filter asFilter() {
        return this;
    }
}
