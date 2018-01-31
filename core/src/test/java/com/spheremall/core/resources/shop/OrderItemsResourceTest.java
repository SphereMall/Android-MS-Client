package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.OrderItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class OrderItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        OrderItem orderItem = client.orderItems().first().data();
        Assert.assertNotNull(orderItem);
        Assert.assertEquals(OrderItem.class.getSimpleName(), orderItem.getClass().getSimpleName());
    }
}
