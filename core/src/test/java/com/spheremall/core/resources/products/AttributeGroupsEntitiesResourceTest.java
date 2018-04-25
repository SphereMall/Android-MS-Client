package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeGroupsEntities;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AttributeGroupsEntitiesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        AttributeGroupsEntities attributeGroupsEntities = client.attributeGroupsEntities()
                .first().data();
        Assert.assertNotNull(attributeGroupsEntities);
        Assert.assertEquals(AttributeGroupsEntities.class.getSimpleName(), attributeGroupsEntities.getClass().getSimpleName());
    }
}