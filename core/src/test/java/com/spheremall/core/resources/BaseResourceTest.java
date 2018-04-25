package com.spheremall.core.resources;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.InPredicate;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.products.ProductResource;
import com.spheremall.core.specifications.base.IsVisible;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseResourceTest extends SetUpResourceTest {

    @Test
    public void testGetSingle() throws SphereMallException, IOException {
        Product item = client.products().first().data();

        Integer productId = item.getId();
        Product product = client.products().get(productId).data();
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getId(), productId);
    }

    @Test
    public void testGetSingleWithFieldsParams() throws SphereMallException, IOException {
        Product product = client.products()
                .fields("price", "title")
                .first().data();
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.title);
        Assert.assertNotSame(product.price, 0);
    }

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<Product> productList = client.products().all().data();
        Assert.assertNotNull(productList);
        Assert.assertEquals(10, productList.size());
    }

    @Test
    public void testIn() {
        ProductResource productResource = client.products()
                .in("id", "6329", "6351");

        InPredicate in = productResource.getIn();
        Assert.assertNotNull(in);
        Assert.assertEquals(in.getField(), "id");
    }

    @Test
    public void testGetListWithIn() throws SphereMallException, IOException {
        List<Product> items = client.products()
                .sort("-id")
                .limit(2)
                .all().data();

        ProductResource productResource = client.products();
        productResource.in("id", items.get(0).getId().toString(), items.get(1).getId().toString());
        productResource.sort("-id");
        List<Product> products = productResource.all().data();
        Assert.assertEquals(products.size(), 2);
        Assert.assertTrue(products.get(0).getId().equals(items.get(0).getId()));
        Assert.assertTrue(products.get(1).getId().equals(items.get(1).getId()));
    }

    @Test
    public void testSort() throws SphereMallException, IOException {

        List<Product> list = client.products()
                .sort("-id")
                .limit(2).all().data();
        org.junit.Assert.assertNotNull(list);
        org.junit.Assert.assertTrue(list.size() > 1);

        ProductResource productResource = client.products();
        productResource.sort("-id");
        productResource.in("id", list.get(0).getId().toString(), list.get(1).getId().toString());
        List<Product> products = productResource.all().data();
        Assert.assertEquals(products.size(), 2);
        Assert.assertTrue(products.get(0).getId().equals(list.get(0).getId()));
        Assert.assertTrue(products.get(1).getId().equals(list.get(1).getId()));
    }

    @Test
    public void testGetListWithFilter() throws SphereMallException, IOException {
        Product product = client.products().first().data();

        ProductResource productResource = client.products();
        productResource.filters(new Predicate("id", FilterOperators.EQUAL, product.getId().toString()));
        List<Product> products = productResource.all().data();
        Assert.assertEquals(products.size(), 1);
        Assert.assertTrue(products.get(0).getId().equals(product.getId()));
    }

    @Test
    public void testGetFilter() {

        ProductResource productResource = client.products()
                .filters(
                        new Predicate("title", FilterOperators.EQUAL, "Product title"));

        List<Predicate> predicates = new ArrayList<Predicate>() {{
            add(new Predicate("title", FilterOperators.EQUAL, "Product title"));
        }};

        Assert.assertEquals(productResource.getFilters(), new Filter(predicates));
    }

    @Test
    public void testGetProductIsVisible() throws SphereMallException, IOException {
        ProductResource productResource = client.products();
        IsVisible isVisible = new IsVisible();
        productResource.filters(isVisible.asFilter());
        List<Product> products = productResource.all().data();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void testGetFilterAsString() {
        ProductResource productResource = client.products();

        productResource.filters(
                new Predicate("title", FilterOperators.EQUAL, "Product title"));

        String singleFilter = productResource.getFilters().toString();

        Assert.assertEquals(singleFilter, "{\"title\":{\"e\":\"Product title\"}}");

        productResource.filters(
                new Predicate("title", FilterOperators.EQUAL, "Product title"),
                new Predicate("age", FilterOperators.NOT_EQUAL, "10"));

        String multipleFilters = productResource.getFilters().toString();

        Assert.assertEquals(multipleFilters, "[{\"title\":{\"e\":\"Product title\"}},{\"age\":{\"ne\":\"10\"}}]");
    }

    @Test
    public void testFilterAsStringFullSearch() {
        final String stringFilter = "[{\"fullSearch\":\"Jake\"}]";
        ProductResource productResource = client.products();
        productResource.filters(new Predicate("fullSearch", null, "Jake"));
        Filter filter = productResource.getFilters();
        Assert.assertEquals(stringFilter, filter.toString());

    }

    @Test
    public void testCount() throws SphereMallException, IOException {
        ProductResource productResource = client.products();
        int count = productResource.count();
        Assert.assertTrue(count > 30);
    }

    @Test
    public void testCreateAndUpdateAndDeleteEntity() throws SphereMallException, IOException {

        HashMap<String, String> params = new HashMap<>();
        params.put("title", "NewProduct");
        Product product = client.products().create(params).data();
        Assert.assertNotNull(product);
        Assert.assertEquals("NewProduct", product.title);

        params = new HashMap<>();
        params.put("title", "UpdatedProduct");
        Product updatedProduct = client.products().update(product.getId(), params).data();
        Assert.assertNotNull(updatedProduct);
        Assert.assertEquals("UpdatedProduct", updatedProduct.title);
        Assert.assertEquals(product.getId(), updatedProduct.getId());

        Assert.assertTrue(client.products().delete(product.getId()));
    }

    @Test
    public void testDeleteEntity() throws SphereMallException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("title", "NewProduct");

        Product product = client.products().create(params).data();
        Assert.assertNotNull(product);
        Assert.assertEquals("NewProduct", product.title);

        Boolean success = client.products().delete(product.getId());
        Assert.assertTrue(success);
    }

    @Test
    public void testFullSearch() throws SphereMallException, IOException {
        List<Product> products = client.products()
                .filters(new Predicate("fullSearch", null, "test"))
                .all().data();
        Assert.assertNotNull(products);
    }
}