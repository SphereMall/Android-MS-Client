package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FunctionalNamesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<FunctionalName> functionalNames = client.functionalNames()
                .limit(3)
                .all().data();

        Assert.assertNotNull(functionalNames);
        Assert.assertEquals(3, functionalNames.size());
        for (FunctionalName item : functionalNames) {
            Assert.assertEquals(FunctionalName.class.getSimpleName(), item.getClass().getSimpleName());
        }
    }
}
