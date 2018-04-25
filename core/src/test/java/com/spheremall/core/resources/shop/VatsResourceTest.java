package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Vat;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class VatsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Vat vat = client.vats().first().data();
        Assert.assertNotNull(vat);
    }
}
