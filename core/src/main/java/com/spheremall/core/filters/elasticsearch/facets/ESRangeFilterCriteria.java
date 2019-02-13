package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.facets.models.ESPriceRangeModel;
import com.spheremall.core.filters.elasticsearch.terms.PriceRangeFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESRangeFilterCriteria implements ESCatalogFilterCriteria {

    private final Map<String, ESPriceRangeModel> attributesRange = new HashMap<>();
    private final Map<String, ESPriceRangeModel> fieldsRange = new HashMap<>();

    private ESRangeFilterCriteria() {
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String name() {
        return "range";
    }

    @Override
    public ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria) {
        throw new RuntimeException("Method will not be implemented");
    }

    @Override
    public JSONObject toJson() throws SphereMallException, JSONException {
        JSONObject rangeObject = new JSONObject();
        JSONObject attrsObject = new JSONObject();
        JSONObject fieldsObject = new JSONObject();

        for (Map.Entry<String, ESPriceRangeModel> entry : attributesRange.entrySet()) {
            JSONObject range = new JSONObject();
            range.put("gte", entry.getValue().minPrice);
            range.put("lte", entry.getValue().maxPrice);
            attrsObject.put(entry.getKey(), range);
        }

        for (Map.Entry<String, ESPriceRangeModel> entry : fieldsRange.entrySet()) {
            JSONObject range = new JSONObject();
            range.put("gte", entry.getValue().minPrice);
            range.put("lte", entry.getValue().maxPrice);
            fieldsObject.put(entry.getKey(), range);
        }
        rangeObject.put("attributes", attrsObject);
        rangeObject.put("fields", fieldsObject);

        JSONObject object = new JSONObject();
        object.put(name(), rangeObject);
        return object;
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        List<ElasticSearchQuery> queries = new ArrayList<>();
        for (Map.Entry<String, ESPriceRangeModel> entry : attributesRange.entrySet()) {
            PriceRangeFilter priceRangeFilter = new PriceRangeFilter(entry.getKey() + "_attr.attributeValue");
            priceRangeFilter.setRange((double) entry.getValue().minPrice, (double) entry.getValue().maxPrice);
            queries.add(priceRangeFilter);
        }

        for (Map.Entry<String, ESPriceRangeModel> entry : fieldsRange.entrySet()) {
            PriceRangeFilter priceRangeFilter = new PriceRangeFilter(entry.getKey());
            priceRangeFilter.setRange((double) entry.getValue().minPrice, (double) entry.getValue().maxPrice);
            queries.add(priceRangeFilter);
        }

        return queries;
    }

    @Override
    public int count() {
        return attributesRange.size() + fieldsRange.size();
    }

    public static class Builder {
        private final Map<String, ESPriceRangeModel> attributesRange = new HashMap<>();
        private final Map<String, ESPriceRangeModel> fieldsRange = new HashMap<>();

        public Builder addFieldRange(String fieldName, long min, long max) {
            ESPriceRangeModel priceRange = new ESPriceRangeModel();
            priceRange.minPrice = min;
            priceRange.maxPrice = max;

            fieldsRange.put(fieldName, priceRange);
            return this;
        }

        public Builder addAttributeRange(String attrName, long min, long max) {
            ESPriceRangeModel priceRange = new ESPriceRangeModel();
            priceRange.minPrice = min;
            priceRange.maxPrice = max;

            attributesRange.put(attrName, priceRange);
            return this;
        }

        public ESRangeFilterCriteria create() {
            ESRangeFilterCriteria criteria = new ESRangeFilterCriteria();
            criteria.attributesRange.putAll(attributesRange);
            criteria.fieldsRange.putAll(fieldsRange);
            return criteria;
        }
    }
}
