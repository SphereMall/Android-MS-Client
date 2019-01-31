package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.api.response.ESFacetsResponse;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.PriceRange;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.FacetsObject;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFacets;
import com.spheremall.core.mappers.FacetAttributesMapper;

import java.util.HashMap;
import java.util.List;

public class ESFacetsMaker implements SingleMaker<ESFacets> {

    @Override
    public Response<ESFacets> makeSingle(String response) {
        Gson gson = new Gson();
        ESFacetsResponse facetsObject = gson.fromJson(response, ESFacetsResponse.class);
        return new Response<>(facetsObject.data, new HashMap<>());
    }
}
