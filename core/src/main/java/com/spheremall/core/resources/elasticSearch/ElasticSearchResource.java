package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilter;
import com.spheremall.core.resources.Resource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchResource extends Resource<ElasticSearchResource, Entity> {

    Response<List<Entity>> search() throws SphereMallException, IOException;

    List<Response<List<Entity>>> multiSearch() throws SphereMallException, IOException;

    Response<List<Entity>> facets(ESCatalogFilter filter, String groupBy, List<String> entities) throws SphereMallException, IOException, JSONException;
}
