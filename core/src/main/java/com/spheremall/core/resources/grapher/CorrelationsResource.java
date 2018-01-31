package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface CorrelationsResource extends Resource<CorrelationsResource, Entity> {

    Response<List<Entity>> getById(int id, Class forClassName) throws EntityNotFoundException, IOException, ServiceException;
}
