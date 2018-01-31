package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.OrderHistoryJson;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.makers.OrderHistoryMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.shop.OrderFinalized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersResourceImpl extends BaseResource<Order, OrdersResource> implements OrdersResource {

    public OrdersResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public OrdersResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "orders";
    }

    @Override
    public ObjectMaker<Order> initializeMaker() {
        return new ObjectMaker<>(Order.class);
    }

    @Override
    protected OrdersResource currentContext() {
        return this;
    }

    @Override
    public OrderFinalized byOrderId(String orderId) throws EntityNotFoundException, ServiceException, IOException {
        return getOrderByParam("byorderid/" + orderId);
    }

    @Override
    public OrderFinalized byId(int id) throws EntityNotFoundException, ServiceException, IOException {
        return getOrderByParam("byid/" + String.valueOf(id));
    }

    @Override
    public List<Order> getHistory(int userId, String orderId) throws EntityNotFoundException, IOException, ServiceException {
        HashMap<String, String> params = getQueryParams();

        String uriAppend = "history/" + userId;
        if (!orderId.isEmpty()) {
            uriAppend += "/" + orderId;
        }

        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        OrderHistoryMaker maker = new OrderHistoryMaker(OrderHistoryJson.class);
        OrderHistoryJson orderHistoryJson = maker.makeSingle(responseMonada.getResponse()).data();
        if (!orderHistoryJson.success) {
            throw new EntityNotFoundException();
        }
        List<Order> orders = new ArrayList<>();
        orders.addAll(orderHistoryJson.data);
        return orders;
    }

    private OrderFinalized getOrderByParam(String uriAppend) throws EntityNotFoundException, IOException, ServiceException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        List<Order> order = maker.makeAsList(responseMonada.getResponse()).data();
        if (order.size() == 0) {
            return null;
        }

        OrderFinalized orderFinalized = new OrderFinalized(smClient);
        orderFinalized.setOrderData(order.get(0));
        return orderFinalized;
    }
}