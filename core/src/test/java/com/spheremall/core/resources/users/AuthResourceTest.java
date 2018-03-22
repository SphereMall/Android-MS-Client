package com.spheremall.core.resources.users;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AuthResourceTest extends SetUpResourceTest {

    @Test
    public void testLogin() throws EntityNotFoundException, ServiceException, IOException {
        User user = client.auth().login("v.chernetsky@spheremall.com", "123");
        Assert.assertNotNull(user);

        Product product = client.products().first().data();
        Assert.assertNotNull(product);
    }
}
