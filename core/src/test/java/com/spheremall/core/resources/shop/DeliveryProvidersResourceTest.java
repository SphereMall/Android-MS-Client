package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class DeliveryProvidersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        DeliveryProvider deliveryProvider = client.deliveryProviders().first().data();
        Assert.assertNotNull(deliveryProvider);
        Assert.assertEquals(DeliveryProvider.class.getSimpleName(), deliveryProvider.getClass().getSimpleName());
    }
}
