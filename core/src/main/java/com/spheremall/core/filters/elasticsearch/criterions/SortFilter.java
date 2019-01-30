package com.spheremall.core.filters.elasticsearch.criterions;

import com.spheremall.core.filters.elasticsearch.common.ESFilterCriteria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SortFilter implements ESFilterCriteria {

    public static final String SORT_KEY = "sort";
    public static final String ORDER_KEY = "order";

    public enum Sort {ASC, DESC}

    public final Map<String, Sort> sortMap = new HashMap<>();

    public SortFilter(String field) {
        this.sortMap.put(field, Sort.ASC);
    }

    public SortFilter(String field, Sort sort) {
        this.sortMap.put(field, sort);
    }

    @Override
    public JSONObject toJson() {
        JSONObject criteriaObject = new JSONObject();
        try {
            JSONArray sortArray = new JSONArray();
            for (Map.Entry<String, Sort> entry : sortMap.entrySet()) {
                JSONObject sortOrderObj = new JSONObject();
                sortOrderObj.put(ORDER_KEY, entry.getValue().name().toLowerCase());

                JSONObject sortFieldObj = new JSONObject();
                sortFieldObj.put(entry.getKey(), sortOrderObj);
                sortArray.put(sortFieldObj);
            }
            criteriaObject.put(SORT_KEY, sortArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return criteriaObject;
    }
}
