package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FunctionalNamesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws EntityNotFoundException, IOException, ServiceException {
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
