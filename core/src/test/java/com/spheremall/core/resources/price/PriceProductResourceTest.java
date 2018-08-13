package com.spheremall.core.resources.price;

import com.spheremall.core.entities.price.PriceValue;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriceProductResourceTest extends SetUpResourceTest {

    @Test
    public void testPriceProductConfigurationFilter() {
        List<AttributeValue> attributeValues = new ArrayList<>();
        AttributeValue attributeValue1 = new AttributeValue();
        attributeValue1.setId(5576);
        attributeValue1.attributeId = 222;
        attributeValues.add(attributeValue1);

        AttributeValue attributeValue2 = new AttributeValue();
        attributeValue2.setId(4804);
        attributeValue2.attributeId = 226;
        attributeValues.add(attributeValue2);

        AttributeValue attributeValue3 = new AttributeValue();
        attributeValue3.setId(4833);
        attributeValue3.attributeId = 227;
        attributeValues.add(attributeValue3);

        PriceConfigurationFilter priceConfigurationFilter = new PriceConfigurationFilter();
        PriceProduct priceProduct = new PriceProduct(951, 1, attributeValues, new ArrayList<>());
        priceConfigurationFilter.addProduct(priceProduct);

        try {
            String data = priceConfigurationFilter.getData();
            System.out.println(data);
        } catch (SphereMallException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindProductPrice() throws IOException, JSONException, SphereMallException {
        List<AttributeValue> attributeValues = new ArrayList<>();

        AttributeValue attributeValue2 = new AttributeValue();
        attributeValue2.setId(3776);
        attributeValue2.attributeId = 226;
        attributeValues.add(attributeValue2);

        AttributeValue attributeValue3 = new AttributeValue();
        attributeValue3.setId(3749);
        attributeValue3.attributeId = 227;
        attributeValues.add(attributeValue3);

        AttributeValue attributeValue1 = new AttributeValue();
        attributeValue1.setId(4337);
        attributeValue1.attributeId = 222;
        attributeValues.add(attributeValue1);

        PriceProduct priceProduct = new PriceProduct(1853, 1, attributeValues, new ArrayList<>());

        PriceValue priceValue = client.productPriceConfiguration().findProductPrice(priceProduct);
        Assert.assertNotNull(priceValue);
    }
}
