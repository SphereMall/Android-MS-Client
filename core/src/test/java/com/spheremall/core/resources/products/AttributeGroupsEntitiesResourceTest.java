package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeGroupsEntities;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AttributeGroupsEntitiesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        AttributeGroupsEntities attributeGroupsEntities = client.attributeGroupsEntities()
                .first().data();
        Assert.assertNotNull(attributeGroupsEntities);
        Assert.assertEquals(AttributeGroupsEntities.class.getSimpleName(), attributeGroupsEntities.getClass().getSimpleName());
    }
}