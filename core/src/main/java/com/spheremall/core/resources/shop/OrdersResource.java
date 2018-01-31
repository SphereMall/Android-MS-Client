package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;
import com.spheremall.core.shop.OrderFinalized;

import java.io.IOException;
import java.util.List;

public interface OrdersResource extends Resource<OrdersResource, Order> {

    OrderFinalized byOrderId(String orderId) throws EntityNotFoundException, ServiceException, IOException;

    OrderFinalized byId(int id) throws EntityNotFoundException, ServiceException, IOException;

    List<Order> getHistory(int userId, String orderId) throws EntityNotFoundException, IOException, ServiceException;
}
