package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;
import com.spheremall.core.shop.AttributesPredicate;
import com.spheremall.core.shop.Basket;
import com.spheremall.core.shop.AddBasketPredicate;
import com.spheremall.core.shop.OrderFinalized;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<Order> orderList = client.orders().all().data();
        Assert.assertNotNull(orderList);
        for (Order order : orderList) {
            Assert.assertEquals(Order.class.getSimpleName(), order.getClass().getSimpleName());
        }
    }

    @Test
    public void testGetOrderById() throws SphereMallException, IOException {
        Order order = client.orders()
                .filters(new Predicate("statusId", FilterOperators.EQUAL, String.valueOf(2)))
                .first().data();

        OrderFinalized orderNew = client.orders().byId(order.getId());
        Assert.assertEquals(order.getId(), Integer.valueOf(orderNew.getId()));
    }

    @Test
    public void testUpdateOrder() throws SphereMallException, IOException {
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
    public void testOrderHistory() throws SphereMallException, IOException {
        User user = client.users().first().data();
        Basket basket = client.basket(-1, user.getId());

        Product product = client.products().first().data();
        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);
        basket.add(basketPredicate);

        HashMap<String, String> params = new HashMap<>();
        params.put("statusId", "2");
        basket.update(params);

        List<Order> orders = client.orders().getHistory(user.getId(), "");
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() > 0);
        for (Order order : orders) {
            Assert.assertEquals(user.getId().intValue(), order.userId);
        }
    }
}