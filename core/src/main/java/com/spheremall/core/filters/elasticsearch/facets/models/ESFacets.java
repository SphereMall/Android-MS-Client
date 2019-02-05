package com.spheremall.core.filters.elasticsearch.facets.models;

import java.util.ArrayList;
import java.util.List;

public class ESFacets {
    public List<ESAttributeModel> attributes = new ArrayList<>();
    public List<ESBrandModel> brands = new ArrayList<>();
    public List<ESFunctionalNameModel> functionalNames = new ArrayList<>();
    public List<ESFactorValueModel> factorValues = new ArrayList<>();
    public List<Integer> priceRange;
    public ESPriceRangeModel priceRangeModel;
}