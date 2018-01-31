package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.CatalogItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class CatalogItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        CatalogItem catalogItem = client.catalogItems().first().data();

        Assert.assertNotNull(catalogItem);
        Assert.assertEquals(CatalogItem.class.getSimpleName(), catalogItem.getClass().getSimpleName());
    }
}
