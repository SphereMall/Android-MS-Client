package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESCatalogConfig;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ESBrandsFilterCriteria implements ESCatalogFilterCriteria {

    public final Set<Integer> brands = new HashSet<>();

    public ESBrandsFilterCriteria(Integer... ids) {
        brands.addAll(Arrays.asList(ids));
    }

    @Override
    public String name() {
        return "brands";
    }

    @Override
    public ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria) {
        return this;
    }

    @Override
    public JSONObject toJson(List<ESCatalogConfig> configs) throws JSONException {
        JSONArray idsJson = new JSONArray();
        for (Integer id : brands) {
            idsJson.put(id);
        }

        JSONObject object = new JSONObject();
        object.put(name(), idsJson);
        return object;
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        List<ElasticSearchQuery> queries = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (Integer id : brands) {
            ids.add(String.valueOf(id));
        }
        TermsFilterCriteria criteria = new TermsFilterCriteria("brandId", ids);
        TermsFilter termsFilter = new TermsFilter(criteria);
        queries.add(termsFilter);
        return queries;
    }

    @Override
    public int count() {
        return 1;
    }
}
