package com.spheremall.core.filters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class InPredicate {
    private final String field;
    private final List<String> options;

    public InPredicate(String field, List<String> options) {
        this.field = field;
        this.options = options;
    }

    public String getField() {
        return field;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (String option : options) {
            jsonArray.put(option);
        }

        try {
            jsonObject.put(field, jsonArray);
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }
}
