package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESAttributesFilterCriteria implements ESCatalogFilterCriteria {

    public final HashMap<String, List<String>> attributes = new HashMap<>();

    protected final String code;
    protected final List<String> values = new ArrayList<>();

    public ESAttributesFilterCriteria(String code, String... value) {
        this.code = code;
        this.values.addAll(Arrays.asList(value));
        this.attributes.put(code, this.values);
    }

    @Override
    public String name() {
        return "attributes";
    }

    @Override
    public ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria) {
        if (newCriteria instanceof ESAttributesFilterCriteria) {
            ESAttributesFilterCriteria attrCriteria = (ESAttributesFilterCriteria) newCriteria;
            attributes.put(attrCriteria.code, attrCriteria.values);
        }
        return this;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject codesJson = new JSONObject();
        for (Map.Entry<String, List<String>> entry : attributes.entrySet()) {
            JSONArray attrValues = new JSONArray();
            for (String value : entry.getValue()) {
                attrValues.put(value);
            }
            JSONObject valueObject = new JSONObject();
            valueObject.put("value", attrValues);
            codesJson.put(entry.getKey(), valueObject);
        }

        JSONObject attributesJson = new JSONObject();
        attributesJson.put(name(), codesJson);
        return attributesJson;
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        List<ElasticSearchQuery> queries = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : attributes.entrySet()) {
            TermsFilterCriteria criteria = new TermsFilterCriteria(entry.getKey() + "_attr.attributeValue", entry.getValue());
            TermsFilter termsFilter = new TermsFilter(criteria);
            queries.add(termsFilter);
        }
        return queries;
    }
}
