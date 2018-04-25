package com.spheremall.core.resources.users;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AuthResourceTest extends SetUpResourceTest {

    @Test
    public void testLogin() throws SphereMallException, IOException {
        User user = client.auth().login("v.chernetsky@spheremall.com", "123");
        Assert.assertNotNull(user);

        Product product = client.products().first().data();
        Assert.assertNotNull(product);
    }
}
