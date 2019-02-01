package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.terms.PriceRangeFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

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
    public ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria) {
        return newCriteria;
    }

    @Override
    public JSONObject toJson() throws SphereMallException {
        try {
            JSONObject object = new JSONObject();
            JSONArray range = new JSONArray();
            object.put("min", min);
            object.put("max", max);

            JSONObject priceRange = new JSONObject();
            priceRange.put(name(), range.put(object));
            return priceRange;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new SphereMallException(e);
        }
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        PriceRangeFilter priceRangeFilter = new PriceRangeFilter("price");
        priceRangeFilter.setRange((double) min, (double) max);
        return Collections.singletonList(priceRangeFilter);
    }
}
