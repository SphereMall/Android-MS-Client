package com.spheremall.core.filters.elasticsearch.criterions;

import com.spheremall.core.filters.elasticsearch.common.ESFilterCriteria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class TermsFilterCriteria implements ESFilterCriteria {

    public final String field;
    public final List<String> values;

    public TermsFilterCriteria(String field, String... values) {
        this.field = field;
        this.values = Arrays.asList(values);
    }

    public TermsFilterCriteria(String field, List<String> values) {
        this.field = field;
        this.values = values;
    }

    @Override
    public JSONObject toJson() {
        JSONObject criteriaObject = new JSONObject();
        try {
            JSONArray valuesArray = new JSONArray();
            for (String value : values) {
                valuesArray.put(value);
            }
            criteriaObject.put(field, valuesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return criteriaObject;
    }
}
