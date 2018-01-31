package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.EntityAttribute;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class EntityAttributesResourceTest extends SetUpResourceTest {

    @Test(expected = EntityNotFoundException.class)
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        EntityAttribute entityAttribute = client.entityAttributes()
                .first().data();

        Assert.assertNotNull(entityAttribute);
        Assert.assertEquals(EntityAttribute.class.getSimpleName(), entityAttribute.getClass().getSimpleName());
    }
}
