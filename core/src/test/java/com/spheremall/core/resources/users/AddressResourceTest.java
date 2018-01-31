package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Address;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AddressResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws EntityNotFoundException, IOException, ServiceException {
        List<Address> addresses = client.addresses().limit(2).all().data();
        Assert.assertNotNull(addresses);
        Assert.assertEquals(2, addresses.size());
        for (Address address : addresses) {
            Assert.assertEquals(Address.class.getSimpleName(), address.getClass().getSimpleName());
        }
    }
}
