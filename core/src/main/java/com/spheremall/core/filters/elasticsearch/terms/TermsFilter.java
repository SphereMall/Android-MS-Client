package com.spheremall.core.filters.elasticsearch.terms;

import com.spheremall.core.filters.elasticsearch.common.ESFilterCriteria;
import com.spheremall.core.filters.elasticsearch.common.LeafQueryFilter;

import org.json.JSONException;
import org.json.JSONObject;

public class TermsFilter extends LeafQueryFilter {

    public final ESFilterCriteria ESFilterCriteria;

    public TermsFilter(ESFilterCriteria ESFilterCriteria) {
        this.ESFilterCriteria = ESFilterCriteria;
        this.name = "terms";
    }

    @Override
    public JSONObject toJson() {
        JSONObject termsFilterJson = new JSONObject();
        try {
            return termsFilterJson.put(name, ESFilterCriteria.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
