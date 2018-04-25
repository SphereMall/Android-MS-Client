package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.CatalogItem;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class CatalogItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        CatalogItem catalogItem = client.catalogItems().first().data();

        Assert.assertNotNull(catalogItem);
        Assert.assertEquals(CatalogItem.class.getSimpleName(), catalogItem.getClass().getSimpleName());
    }
}
