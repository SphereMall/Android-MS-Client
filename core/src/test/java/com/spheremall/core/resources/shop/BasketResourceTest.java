package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class BasketResourceTest extends SetUpResourceTest {

    @Override
    public void setUp() throws SphereMallException, IOException {
        super.setUp();
    }

    @Test
    public void testGetById() throws SphereMallException, IOException {
        int basketId = client.basket().getId();
        Order basket = client.basketResource().get(basketId).data();
        Assert.assertNotNull(basket);
        Assert.assertEquals(Integer.valueOf(basketId), basket.getId());
    }

    @Test
    public void testGetByUserId() throws SphereMallException, IOException {
        User user = client.users().first().data();
        BasketOrder basket = client.basketResource().getByUserId(user.getId()).data();
        Assert.assertNotNull(basket);
        Assert.assertEquals(user.getId().intValue(), basket.userId);
    }

    @Test
    public void testCreateNew() throws SphereMallException, IOException {
        Order order = client.basketResource().createNew();
        Assert.assertNotNull(order);
    }

    @Test
    public void testRemoveItems() throws SphereMallException, IOException {

        int basketId = client.basket().getId();

        Product product = client.products().first().data();
        HashMap<String, String> params = new HashMap<>();
//        params.put("products", "[{\"id\":" + product.getId() + ", \"amount\":1, \"attributes\":[]}]");
        params.put("products", "[\n" +
                "    {\n" +
                "        \"id\": 405,\n" +
                "        \"amount\": 2,\n" +
                "        \"attributes\":[\n" +
                "            {\n" +
                "                \"attributeId\":226, \n" +
                "                \"attributeValueId\":3776,\n" +
                "                \"userValue\": 73\n" +
                "            },\n" +
                "            {\n" +
                "                \"attributeId\":227, \n" +
                "                \"attributeValueId\":3749,\n" +
                "                \"userValue\": 35\n" +
                "            },\n" +
                "            {\n" +
                "                \"attributeId\":222, \n" +
                "                \"attributeValueId\":4337\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]");

        params.put("basketId", String.valueOf(basketId));
        BasketOrder basketOrder = client.basketResource().create(params).data();
        Assert.assertNotNull(basketOrder);
        Assert.assertTrue(basketOrder.items.size() > 0);
        Assert.assertEquals(product.getId().intValue(), basketOrder.items.get(0).productId);

        params = new HashMap<>();
        params.put("basketId", String.valueOf(basketId));
        params.put("products", "[{\"id\":\"" + product.getId() + "\"}]");
        Order order = client.basketResource().removeItems(params);
        Assert.assertNotNull(order);
    }
}