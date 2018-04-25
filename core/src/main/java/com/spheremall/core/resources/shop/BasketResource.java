package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.HashMap;

public interface BasketResource extends Resource<BasketResource, BasketOrder> {

    Response<BasketOrder> getByUserId(int userId) throws SphereMallException, IOException;

    BasketOrder createNew() throws SphereMallException, IOException;

    BasketOrder removeItems(HashMap<String, String> data) throws SphereMallException, IOException;
}
