package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchResource extends Resource<ElasticSearchResource, Entity> {

    List<Entity> fetch() throws SphereMallException, IOException;

    String allTest() throws SphereMallException, IOException;

    Response<Facets> facets() throws SphereMallException, IOException;
}
