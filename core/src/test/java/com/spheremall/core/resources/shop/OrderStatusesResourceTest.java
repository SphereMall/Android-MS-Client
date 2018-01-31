package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.OrderStatus;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class OrderStatusesResourceTest extends SetUpResourceTest {

    @Test(expected = EntityNotFoundException.class)
    public void testGetList() throws EntityNotFoundException, IOException, ServiceException {
        List<OrderStatus> orderStatuses = client.orderStatuses().limit(2).all().data();
        Assert.assertNotNull(orderStatuses);
        Assert.assertTrue(orderStatuses.size() > 0);
    }
}
