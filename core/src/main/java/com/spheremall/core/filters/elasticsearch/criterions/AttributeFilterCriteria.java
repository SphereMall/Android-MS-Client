package com.spheremall.core.filters.elasticsearch.criterions;

import com.spheremall.core.filters.elasticsearch.common.ESFilterCriteria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class AttributeFilterCriteria implements ESFilterCriteria {

    public final int attribute;
    public final List<Integer> values;

    public AttributeFilterCriteria(int attribute, Integer... values) {
        this.attribute = attribute;
        this.values = Arrays.asList(values);
    }

    public AttributeFilterCriteria(int attribute, List<Integer> values) {
        this.attribute = attribute;
        this.values = values;
    }

    @Override
    public JSONObject toJson() {
        JSONObject criteriaObject = new JSONObject();
        try {
            JSONArray valuesArray = new JSONArray();
            for (Integer value : values) {
                valuesArray.put(value);
            }

            String attr = attribute + "_attr.valueId";
            criteriaObject.put(attr, valuesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return criteriaObject;
    }
}
