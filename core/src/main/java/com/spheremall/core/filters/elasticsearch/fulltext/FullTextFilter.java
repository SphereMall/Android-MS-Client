package com.spheremall.core.filters.elasticsearch.fulltext;

import com.spheremall.core.filters.elasticsearch.common.LeafQueryFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class FullTextFilter extends LeafQueryFilter {
    protected static final String QUERY = "query";

    protected Map<String, Object> queryParams = new LinkedHashMap<>();
    protected final String field;

    protected FullTextFilter(String field, String query) {
        this.field = field;
        this.queryParams.put(QUERY, query);
    }

    @Override
    public JSONObject toJson() {
        JSONObject matchObject = new JSONObject();
        try {
            JSONObject propertiesObject = new JSONObject();

            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                propertiesObject.put(entry.getKey(), entry.getValue());
            }

            JSONObject messageObject = new JSONObject();
            if (propertiesObject.length() == 1) {
                messageObject.put(field, queryParams.get(QUERY));
            } else {
                messageObject.put(field, propertiesObject);
            }

            matchObject.put(name, messageObject);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        return matchObject;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
