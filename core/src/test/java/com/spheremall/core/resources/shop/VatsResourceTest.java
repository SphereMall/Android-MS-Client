package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Vat;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class VatsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Vat vat = client.vats().first().data();
        Assert.assertNotNull(vat);
    }
}
