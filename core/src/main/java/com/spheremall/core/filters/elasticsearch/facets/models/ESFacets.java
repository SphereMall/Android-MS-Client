package com.spheremall.core.filters.elasticsearch.facets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ESFacets {
    public List<ESAttributeModel> attributes = new ArrayList<>();
    public List<ESBrandModel> brands = new ArrayList<>();
    public List<ESFunctionalNameModel> functionalNames = new ArrayList<>();
    public List<ESFactorValueModel> factorValues = new ArrayList<>();
    public ESPriceRangeModel priceRange;

    public JSONArray buildParams() {

        JSONObject paramsObject = new JSONObject();
        try {
            if (priceRange != null) {
                paramsObject.put("priceRange", priceRangeObject());
            }
            if (attributes.size() > 0) {
                paramsObject.put("attributes", attributesObject());
            }
            if (brands.size() > 0) {
                paramsObject.put("brands", brandsObject());
            }
            if (functionalNames.size() > 0) {
                paramsObject.put("functionalNames", functionalNamesObject());
            }

            if (factorValues.size() > 0) {
                paramsObject.put("factorValues", factorValuesObject());
            }

            JSONArray params = new JSONArray();
            params.put(paramsObject);
            return params;
        } catch (JSONException e) {
            System.out.println(e.getLocalizedMessage());
            return new JSONArray();
        }
    }

    public JSONObject attributesObject() throws JSONException {
        if (attributes.isEmpty()) return new JSONObject();

        JSONObject object = new JSONObject();
        HashSet<String> processedAttrCodes = new HashSet<>();

        for (ESAttributeModel attribute : attributes) {
            if (processedAttrCodes.contains(attribute.code)) continue;

            processedAttrCodes.add(attribute.code);

            JSONArray attrsArray = new JSONArray();
            for (ESAttributeModel nestedAttr : attributes) {
                if (attribute.code.equals(nestedAttr.code)) {
                    attrsArray.put(nestedAttr.value);
                }
            }
            JSONObject value = new JSONObject();
            value.put("value", attrsArray);
            object.put(attribute.code, value);
        }
        return object;
    }

    public JSONArray priceRangeObject() throws JSONException {
        if (priceRange == null) return new JSONArray();

        JSONObject object = new JSONObject();
        JSONArray range = new JSONArray();
        object.put("min", priceRange.minPrice);
        object.put("max", priceRange.maxPrice);
        return range.put(object);
    }

    public JSONArray brandsObject() {
        if (brands.size() == 0) return new JSONArray();

        JSONArray brandsObject = new JSONArray();
        for (ESBrandModel brand : brands) {
            brandsObject.put(brand.id);
        }
        return brandsObject;
    }

    public JSONArray factorValuesObject() {
        if (factorValues.size() == 0) return new JSONArray();

        JSONArray factorValuesObject = new JSONArray();
        for (ESBrandModel brand : brands) {
            factorValuesObject.put(brand.id);
        }
        return factorValuesObject;
    }

    public JSONArray functionalNamesObject() {
        if (functionalNames.size() == 0) return new JSONArray();

        JSONArray functionalNamesObject = new JSONArray();
        for (ESBrandModel brand : brands) {
            functionalNamesObject.put(brand.id);
        }
        return functionalNamesObject;
    }
}