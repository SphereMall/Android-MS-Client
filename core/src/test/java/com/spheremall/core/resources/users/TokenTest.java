package com.spheremall.core.resources.users;

import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class TokenTest extends SetUpResourceTest{

    @Test
    public void getTokenTest() throws EntityNotFoundException, ServiceException, IOException {
        String token = client.auth().login("testEmail", "testPassword");
        Assert.assertNotNull(token);
        Assert.assertTrue(!token.isEmpty());
    }

}
