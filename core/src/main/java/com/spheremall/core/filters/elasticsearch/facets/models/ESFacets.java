package com.spheremall.core.filters.elasticsearch.facets.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESFacets {
    public List<ESAttributeModel> attributes = new ArrayList<>();
    public List<ESBrandModel> brands = new ArrayList<>();
    public List<ESFunctionalNameModel> functionalNames = new ArrayList<>();
    public List<ESFactorValueModel> factorValues = new ArrayList<>();
    public ArrayList<HashMap<String, ESRangeModel>> range;
}