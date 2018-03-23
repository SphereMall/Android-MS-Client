package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;

public interface GridResource extends Resource<GridResource, Entity> {

    Response<Facets> facets() throws EntityNotFoundException, IOException, ServiceException;

    @Override
    int count() throws EntityNotFoundException, IOException, ServiceException;
}