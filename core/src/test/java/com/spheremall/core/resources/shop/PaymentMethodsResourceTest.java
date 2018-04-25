package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.PaymentMethod;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PaymentMethodsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<PaymentMethod> paymentMethods = client.paymentMethods().limit(2).all().data();
        Assert.assertNotNull(paymentMethods);
        Assert.assertTrue(paymentMethods.size() > 0);
    }
}
