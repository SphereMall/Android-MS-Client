package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class TokenTest extends SetUpResourceTest {

    @Test(expected = EntityNotFoundException.class)
    public void getTokenTest() throws SphereMallException, IOException {
        User user = client.auth().login("testEmail", "testPassword");
        Assert.assertNotNull(user);
    }

}
