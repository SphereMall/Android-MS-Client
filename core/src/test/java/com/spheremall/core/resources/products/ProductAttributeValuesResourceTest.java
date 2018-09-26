package com.spheremall.core.resources.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.filters.grid.EntityFilter;
import com.spheremall.core.filters.grid.GridFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ProductAttributeValuesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        ProductAttributeValue productAttributeValue = client.productAttributeValues()
                .first().data();

        Assert.assertNotNull(productAttributeValue);
        Assert.assertEquals(ProductAttributeValue.class.getSimpleName(), productAttributeValue.getClass().getSimpleName());
    }

    @Test
    public void testGetValuesForProduct() throws SphereMallException, IOException {
        GridFilter gridFilter = new GridFilter();
        EntityFilter entityFilter = new EntityFilter("products");
        gridFilter.elements(entityFilter);

        Entity product = client.grid()
                .filters(gridFilter)
                .all().data().get(0);

        Assert.assertNotNull(product);

        List<ProductAttributeValue> productAttributeValues = client.productAttributeValues()
                .filters(new Predicate("productId", FilterOperators.EQUAL, product.getId().toString()))
                .all().data();

        Assert.assertNotNull(productAttributeValues);
    }
}
