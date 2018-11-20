package com.spheremall.core.resources.products;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductVariantsContainer;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductResourceTest extends SetUpResourceTest {
    @Test
    public void testProductDetail() throws SphereMallException, IOException {

        Product testProduct = client.products().first().data();

        List<Product> products = client.products()
                .detail().data();

        Assert.assertNotNull(products);

        products = client.products()
                .limit(1)
                .ids(testProduct.getId())
                .detail().data();

        Assert.assertEquals(1, products.size());
        Assert.assertEquals(testProduct.getId(), products.get(0).getId());

        Product product = client.products()
                .filters(new Predicate("showInSpecList", FilterOperators.EQUAL, "1"))
                .detail(testProduct.getId());

        Assert.assertEquals(testProduct.getId().intValue(), product.getId().intValue());

        product = client.products()
                .detail(testProduct.urlCode);

        Assert.assertEquals(testProduct.urlCode, product.urlCode);
    }

    @Test
    public void testProductDetailList() throws IOException, SphereMallException {
        Response<List<Product>> productsResponse = client.products()
                .limit(10)
                .offset(0)
                .detail();

        Assert.assertNotNull(productsResponse);
        Assert.assertEquals("10", productsResponse.meta().get("limit").toString());
        Assert.assertEquals("0", productsResponse.meta().get("offset").toString());
    }

    @Test
    public void testProductDetailById() throws IOException, SphereMallException {
        Product productsResponse = client.products()
                .detail(1080);

        Assert.assertNotNull(productsResponse);
    }

    @Test
    public void testVariants() throws IOException, SphereMallException {
        List<String> codes = new ArrayList<>();
        codes.add("frametype");
        codes.add("colorcom");
        codes.add("colorbas");

        List<ProductVariantsContainer> containers = client.products().variants(Arrays.asList(4108, 4509, 4597), codes);
        Assert.assertNotNull(containers);

        List<Integer> ids = new ArrayList<>();
        ids.add(1096);
        Response<List<Product>> variants = client.products().variants(ids);
        Assert.assertNotNull(variants);
        Assert.assertNotNull(variants.data());
        Assert.assertTrue(variants.data().size() > 0);
    }
}