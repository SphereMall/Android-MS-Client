package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;

public interface GridResource extends Resource<GridResource, Entity> {

    Response<Facets> facets() throws SphereMallException, IOException;

    @Override
    int count() throws SphereMallException, IOException;
}