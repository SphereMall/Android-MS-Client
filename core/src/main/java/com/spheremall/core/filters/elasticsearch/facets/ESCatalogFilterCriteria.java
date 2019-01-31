package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;

public interface ESCatalogFilterCriteria {
    String name();

    String toJson() throws SphereMallException;
}