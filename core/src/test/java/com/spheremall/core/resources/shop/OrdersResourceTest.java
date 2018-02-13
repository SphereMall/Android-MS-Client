package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;
import com.spheremall.core.shop.OrderFinalized;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrdersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws EntityNotFoundException, IOException, ServiceException {
        List<Order> orderList = client.orders().all().data();
        Assert.assertNotNull(orderList);
        for (Order order : orderList) {
            Assert.assertEquals(Order.class.getSimpleName(), order.getClass().getSimpleName());
        }
    }

    @Test
    public void testGetOrderById() throws EntityNotFoundException, IOException, ServiceException {
        Order order = client.orders()
                .filters(new Predicate("statusId", FilterOperators.EQUAL, String.valueOf(2)))
                .first().data();

        OrderFinalized orderNew = client.orders().byId(order.getId());
        Assert.assertEquals(order.getId(), Integer.valueOf(orderNew.getId()));
    }

    @Test
    public void testUpdateOrder() throws EntityNotFoundException, IOException, ServiceException {
        Order order = client.orders()
                .filters(new Predicate("statusId", FilterOperators.NOT_EQUAL, String.valueOf(2)))
                .first().data();

        OrderFinalized orderNew = client.orders().byId(order.getId());

        HashMap<String, String> params = new HashMap<>();
        params.put("paymentStatusId", String.valueOf(2));
        orderNew.update(params);
        Assert.assertEquals(2, orderNew.getOrder().paymentStatusId);
    }

    @Test
    public void testOrderHistory() throws EntityNotFoundException, ServiceException, IOException {
        List<Order> orders = client.orders().getHistory(227, "");
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() > 0);
        for (Order order : orders) {
            Assert.assertEquals(227, order.userId);
        }
    }
}