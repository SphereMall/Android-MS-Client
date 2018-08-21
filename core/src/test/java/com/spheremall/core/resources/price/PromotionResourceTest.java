package com.spheremall.core.resources.price;

import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.ProductsToPromotions;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionResourceTest extends SetUpResourceTest {

    @Test
    public void testPrice() throws IOException, JSONException, SphereMallException {
        List<AttributeValue> attributeValues = new ArrayList<>();

        Product product = client.products().detail(1853);
        for (Attribute attribute : product.affectedAttributes) {
            attributeValues.add(attribute.attributeValues.get(0));
        }

        PriceProduct priceProduct = new PriceProduct(1853, 1, attributeValues, new ArrayList<>());

        ProductsToPromotions productsToPromotions = client.promotions().price(priceProduct);
        Assert.assertNotNull(productsToPromotions);

        Double vat = 20.0;
        Double result = 0.0;
        Double resultOld = 0.0;

        if (productsToPromotions.discountTypeId == 2) {
            Double k = productsToPromotions.price * (1 + (vat / 100.0));
            result = Math.round(k - productsToPromotions.discountPrice) / 100D;
        } else {
            result = Math.round(productsToPromotions.itemPrice * (1 + (vat / 100.0))) / 100D;
        }

        resultOld = Math.round(productsToPromotions.price * (1 + (vat / 100.0))) / 100.0;
        Assert.assertTrue(result > 0);
        Assert.assertTrue(resultOld > 0);
    }
}
