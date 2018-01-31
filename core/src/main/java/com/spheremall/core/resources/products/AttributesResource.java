package com.spheremall.core.resources.products;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface AttributesResource extends Resource<AttributesResource, Attribute> {

    Response<List<Attribute>> belong(Class entityClass) throws EntityNotFoundException, IOException, ServiceException;

    Response<List<Attribute>> belong(Class entityClass, int groupId) throws EntityNotFoundException, IOException, ServiceException;

    Response<Attribute> belong(Class entityClass, int groupId, int attributeId) throws EntityNotFoundException, IOException, ServiceException;
}
