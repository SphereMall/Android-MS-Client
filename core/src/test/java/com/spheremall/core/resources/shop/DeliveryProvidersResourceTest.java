package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class DeliveryProvidersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        DeliveryProvider deliveryProvider = client.deliveryProviders().first().data();
        Assert.assertNotNull(deliveryProvider);
        Assert.assertEquals(DeliveryProvider.class.getSimpleName(), deliveryProvider.getClass().getSimpleName());
    }
}
