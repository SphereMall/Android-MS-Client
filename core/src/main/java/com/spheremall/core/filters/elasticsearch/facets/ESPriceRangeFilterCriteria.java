package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ESPriceRangeFilterCriteria implements ESCatalogFilterCriteria {

    public final int min;
    public final int max;

    public ESPriceRangeFilterCriteria(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String name() {
        return "priceRange";
    }

    @Override
    public String toJson() throws SphereMallException {
        try {
            JSONObject object = new JSONObject();
            JSONArray range = new JSONArray();
            object.put("min", min);
            object.put("max", max);
            return range.put(object).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            throw new SphereMallException(e);
        }
    }
}
