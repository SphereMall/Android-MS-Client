package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFacets;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchResource extends Resource<ElasticSearchResource, Entity> {

    Response<List<Entity>> fetch() throws SphereMallException, IOException;

    Response<List<Entity>> search(String query) throws SphereMallException, IOException;

    Response<List<Entity>> search(String query, List<String> indexes) throws SphereMallException, IOException;

    Response<List<Entity>> search(String query, ESSearchFilter filter) throws SphereMallException, IOException;

    Response<ESFacets> facets() throws SphereMallException, IOException;
}
