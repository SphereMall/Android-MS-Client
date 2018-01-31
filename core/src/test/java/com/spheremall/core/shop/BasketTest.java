package com.spheremall.core.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BasketTest extends SetUpResourceTest {

    private static final int BASKET_ID = 1862;

    @Test
    public void testBasketCreate() throws EntityNotFoundException, IOException, ServiceException {

        Product product = client.products().first().data();
        Assert.assertNotNull(product);

        Basket basket = client.basket();
        Assert.assertNotNull(basket);

        basket.add(new BasketPredicate(product.getId(), 1));

        Assert.assertEquals(1, basket.items.size());
        Assert.assertEquals(1, basket.items.get(0).amount);
    }

    @Test
    public void testGetExistingBasket() throws EntityNotFoundException, ServiceException, IOException {
        Basket basket = client.basket(BASKET_ID);
        Assert.assertNotNull(basket);
        Assert.assertEquals(basket.id, BASKET_ID);
    }

    @Test
    public void testItemRemoveFromBasket() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();

        Assert.assertNotNull(product);

        Basket basket = client.basket();
        Assert.assertNotNull(basket);

        basket.add(new BasketPredicate(product.getId(), 1));

        Assert.assertNotNull(basket.items);
        Assert.assertEquals(2, basket.items.size());

        basket.remove(new BasketPredicate(product.getId()));

        Assert.assertEquals(1, basket.items.size());
    }

    @Test
    public void testBasketUpdate() throws EntityNotFoundException, ServiceException, IOException {
        Product product = client.products().first().data();
        Assert.assertNotNull(product);

        Basket basket = client.basket(BASKET_ID);
        Assert.assertNotNull(basket);

        basket.remove(new BasketPredicate(product.getId()));

        basket.add(new BasketPredicate(product.getId()));
        Assert.assertEquals(1, basket.items.size());
        Assert.assertEquals(1, basket.items.get(0).amount);

        BasketPredicate predicate = new BasketPredicate(basket.items.get(0).products.get(0).getId(), 3);
        basket.update(predicate);

        Assert.assertEquals(2, basket.items.size());
        Assert.assertEquals(3, basket.items.get(0).amount);

        basket.remove(new BasketPredicate(product.getId()));
    }
}
