package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESCatalogConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESRangeConfig;
import com.spheremall.core.filters.elasticsearch.facets.models.ESRangeModel;
import com.spheremall.core.filters.elasticsearch.terms.PriceRangeFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESRangeFilterCriteria implements ESCatalogFilterCriteria {

    private final Map<String, ESRangeModel> attributesRange = new HashMap<>();
    private final Map<String, ESRangeModel> fieldsRange = new HashMap<>();

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
    public JSONObject toJson(List<ESCatalogConfig> configs) throws JSONException {
        JSONObject rangeObject = new JSONObject();
        JSONObject attrsObject = new JSONObject();
        JSONObject fieldsObject = new JSONObject();

        for (Map.Entry<String, ESRangeModel> entry : attributesRange.entrySet()) {
            JSONObject range = new JSONObject();
            range.put("gte", entry.getValue().min);
            range.put("lte", entry.getValue().max);
            attrsObject.put(findRangeAttribute(configs, entry.getKey()), range);
        }

        for (Map.Entry<String, ESRangeModel> entry : fieldsRange.entrySet()) {
            JSONObject range = new JSONObject();
            range.put("gte", entry.getValue().min);
            range.put("lte", entry.getValue().max);
            fieldsObject.put(entry.getKey(), range);
        }
        if (attrsObject.keys().hasNext()) {
            rangeObject.put("attributes", attrsObject);
        }
        if (fieldsObject.keys().hasNext()) {
            rangeObject.put("fields", fieldsObject);
        }

        JSONObject object = new JSONObject();
        object.put(name(), rangeObject);
        return object;
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        List<ElasticSearchQuery> queries = new ArrayList<>();
        for (Map.Entry<String, ESRangeModel> entry : attributesRange.entrySet()) {
            PriceRangeFilter priceRangeFilter = new PriceRangeFilter(entry.getKey());
            priceRangeFilter.setRange((double) entry.getValue().min, (double) entry.getValue().max);
            queries.add(priceRangeFilter);
        }

        for (Map.Entry<String, ESRangeModel> entry : fieldsRange.entrySet()) {
            PriceRangeFilter priceRangeFilter = new PriceRangeFilter(entry.getKey());
            priceRangeFilter.setRange((double) entry.getValue().min, (double) entry.getValue().max);
            queries.add(priceRangeFilter);
        }

        return queries;
    }

    @Override
    public int count() {
        return attributesRange.size() + fieldsRange.size();
    }

    private String findRangeAttribute(List<ESCatalogConfig> configs, String key) {
        for (ESCatalogConfig config : configs) {
            if (config instanceof ESRangeConfig) {
                ESRangeConfig rangeConfig = (ESRangeConfig) config;
                for (String attrCode : rangeConfig.attrCodes) {
                    if (key.contains(attrCode))
                        return attrCode;
                }
            }
        }
        return key;
    }

    public static class Builder {
        private final Map<String, ESRangeModel> attributesRange = new HashMap<>();
        private final Map<String, ESRangeModel> fieldsRange = new HashMap<>();

        public Builder addFieldRange(String fieldName, long min, long max) {
            ESRangeModel priceRange = new ESRangeModel();
            priceRange.min = min;
            priceRange.max = max;

            fieldsRange.put(fieldName, priceRange);
            return this;
        }

        public Builder addAttributeRange(String attrName, long min, long max) {
            ESRangeModel priceRange = new ESRangeModel();
            priceRange.min = min;
            priceRange.max = max;

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
