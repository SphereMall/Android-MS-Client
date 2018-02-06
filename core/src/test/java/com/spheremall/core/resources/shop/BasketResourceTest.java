package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class BasketResourceTest extends SetUpResourceTest {

    private Product product;
    private static final int BASKET_ID = 1862;

    @Override
    public void setUp() throws EntityNotFoundException, ServiceException, IOException {
        super.setUp();
        product = client.products().first().data();
    }

    @Test
    public void testGetById() throws EntityNotFoundException, ServiceException, IOException {
        Order basket = client.basketResource().get(BASKET_ID).data();
        Assert.assertNotNull(basket);
        Assert.assertEquals(Integer.valueOf(BASKET_ID), basket.getId());
    }

    @Test
    public void testGetByUserId() throws EntityNotFoundException, ServiceException, IOException {
        Order basket = client.basketResource().getByUserId(5).data();
        Assert.assertNotNull(basket);
        Assert.assertEquals(5, basket.userId);
    }

    @Test
    public void testCreateNew() throws EntityNotFoundException, ServiceException, IOException {
        Order order = client.basketResource().createNew();
        Assert.assertNotNull(order);
    }

    @Test
    public void testRemoveItems() throws EntityNotFoundException, ServiceException, IOException {

        HashMap<String, String> params = new HashMap<>();
        params.put("products", "[{\"id\":6354, \"amount\":1}]");
        params.put("basketId", String.valueOf(BASKET_ID));
        BasketOrder basketOrder = client.basketResource().create(params).data();
        Assert.assertNotNull(basketOrder);
        Assert.assertTrue(basketOrder.items.size() > 0);
        Assert.assertEquals(6354, basketOrder.items.get(0).productId);

        params = new HashMap<>();
        params.put("basketId", String.valueOf(BASKET_ID));
        params.put("products", "[{\"id\":\"6354\"}]");
        Order order = client.basketResource().removeItems(params);
        Assert.assertNotNull(order);
    }
}