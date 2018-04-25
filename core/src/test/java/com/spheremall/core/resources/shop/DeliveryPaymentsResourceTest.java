package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.DeliveryPaymentRelation;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class DeliveryPaymentsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        DeliveryPaymentRelation deliveryPaymentRelation = client.deliveryPayments().first().data();
        Assert.assertNotNull(deliveryPaymentRelation);
    }
}