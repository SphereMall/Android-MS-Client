package com.spheremall.core.makers;

import com.google.gson.Gson;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.PriceRange;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.FacetsObject;
import com.spheremall.core.mappers.FacetAttributesMapper;

import java.util.HashMap;

public class FacetsMaker extends ObjectMaker<Facets> {

    public FacetsMaker(Class<Facets> clazz) {
        super(clazz);
    }

    @Override
    public Response<Facets> makeSingle(String response) {
        Gson gson = new Gson();
        FacetsObject facetsObject = gson.fromJson(response, FacetsObject.class);

        FacetAttributesMapper attributesMapper = new FacetAttributesMapper();
        PriceRange priceRange = new PriceRange(
                facetsObject.data.priceRange.minPrice,
                facetsObject.data.priceRange.maxPrice,
                facetsObject.data.priceRange.amounts);

        Facets facets = new Facets(attributesMapper.doObject(facetsObject), priceRange);
        return new Response<>(facets, new HashMap<>());
    }
}
