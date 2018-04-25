package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CorrelationsResourceTest extends SetUpResourceTest {

    @Test
    public void testCorrelations() throws SphereMallException, IOException {
        List<Entity> entities = client.correlations().getById(4, Product.class).data();
        Assert.assertNotNull(entities);
        //TODO: Stub methods in CorrelationsResource: delete, create, etc.
    }
}
