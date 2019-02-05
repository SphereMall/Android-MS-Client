package com.spheremall.core.filters.elasticsearch.compound;

import com.spheremall.core.filters.elasticsearch.common.CompoundQueryFilter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoolFilter extends CompoundQueryFilter {

    private static final String MUST = "must";
    private static final String SHOULD = "should";
    private static final String MUST_NOT = "must_not";
    private static final String FILTER = "filter";

    public final LinkedHashMap<String, ArrayList<ElasticSearchQuery>> elements;

    public BoolFilter() {
        this.name = "bool";
        this.elements = new LinkedHashMap<>();
    }

    public void must(ElasticSearchQuery... elements) {
        if (this.elements.containsKey(MUST)) {
            this.elements.get(MUST).addAll(Arrays.asList(elements));
        } else {
            this.elements.put(MUST, new ArrayList<>(Arrays.asList(elements)));
        }
    }

    public void should(ElasticSearchQuery... elements) {
        if (this.elements.containsKey(SHOULD)) {
            this.elements.get(SHOULD).addAll(Arrays.asList(elements));
        } else {
            this.elements.put(SHOULD, new ArrayList<>(Arrays.asList(elements)));
        }
    }

    public void mustNot(ElasticSearchQuery... elements) {
        if (this.elements.containsKey(MUST_NOT)) {
            this.elements.get(MUST_NOT).addAll(Arrays.asList(elements));
        } else {
            this.elements.put(MUST_NOT, new ArrayList<>(Arrays.asList(elements)));
        }
    }

    public void filter(ElasticSearchQuery... elements) {
        if (this.elements.containsKey(FILTER)) {
            this.elements.get(FILTER).addAll(Arrays.asList(elements));
        } else {
            this.elements.put(FILTER, new ArrayList<>(Arrays.asList(elements)));
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject boolFilterJson = new JSONObject();

        for (Map.Entry<String, ArrayList<ElasticSearchQuery>> entry : elements.entrySet()) {
            if (entry.getValue().size() == 0) continue;
            try {
                if (entry.getValue().size() > 1) {
                    boolFilterJson.put(entry.getKey(), elementsToArray(entry.getValue()));
                } else {
                    boolFilterJson.put(entry.getKey(), entry.getValue().get(0).toJson());
                }
            } catch (JSONException error) {
                error.printStackTrace();
            }
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(name, boolFilterJson);
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONArray elementsToArray(ArrayList<ElasticSearchQuery> filterElements) {
        JSONArray filterJson = new JSONArray();
        for (ElasticSearchQuery item : filterElements) {
            filterJson.put(item.toJson());
        }
        return filterJson;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
