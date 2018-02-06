package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.HashMap;

public interface BasketResource extends Resource<BasketResource, BasketOrder> {

    Response<BasketOrder> getByUserId(int userId) throws ServiceException, IOException, EntityNotFoundException;

    BasketOrder createNew() throws EntityNotFoundException, IOException, ServiceException;

    BasketOrder removeItems(HashMap<String, String> data) throws EntityNotFoundException, IOException, ServiceException;
}
