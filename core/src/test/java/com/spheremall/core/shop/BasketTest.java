package com.spheremall.core.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.PaymentMethod;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Test;

import java.io.IOException;

public class BasketTest extends SetUpResourceTest {

    private static final int userId = 5;
    private static final int basketId = 1862;

    @Override
    public void setUp() throws EntityNotFoundException, ServiceException, IOException {
        super.setUp();
        client.clearBasket();
    }

    @Test
    public void testCreateBasket() throws EntityNotFoundException, ServiceException, IOException {
        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(0, basket.getUserId());
    }

    @Test
    public void testGetByUserId() throws EntityNotFoundException, ServiceException, IOException {
        Basket basket = client.basket(Basket.DEFAULT_ORDER_ID, userId);
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(userId, basket.getUserId());
    }

    @Test
    public void testGetByBasketId() throws EntityNotFoundException, ServiceException, IOException {
        Basket basket = client.basket(basketId);
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(basketId, basket.getId());
    }

    @Test
    public void testAddItemToBasket() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);
    }

    @Test
    public void testRemoveItemFromBasket() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        order = basket.remove(new BasketPredicate(product.getId()));
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertTrue(order.items.size() == 0);
    }

    @Test
    public void testUpdateItemAmountInBasket() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        order = basket.update(new BasketPredicate(product.getId(), 3));
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertTrue(order.items.size() == 1);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 3);

        order = basket.update(new BasketPredicate(product.getId(), -2));
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertTrue(order.items.size() == 1);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 1);
    }

    @Test
    public void testSetUser() throws EntityNotFoundException, IOException, ServiceException {
        User user = client.users()
                .filters(new Predicate("id", FilterOperators.NOT_EQUAL, String.valueOf(userId)))
                .first().data();

        junit.framework.Assert.assertNotNull(user);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        basket.setUser(user.getId()).update();
        junit.framework.Assert.assertEquals(user.getId().intValue(), basket.getUserId());
    }

    @Test
    public void testDelivery() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        DeliveryProvider deliveryProvider = client.deliveryProviders().first().data();
        junit.framework.Assert.assertNotNull(deliveryProvider);

        junit.framework.Assert.assertNotNull(basket);

        order = basket.setDelivery(new Delivery(deliveryProvider)).update();
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertEquals(order.deliveryProviderId, deliveryProvider.getId().intValue());
    }

    @Test
    public void testShipping() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        Address address = client.addresses().first().data();
        junit.framework.Assert.assertNotNull(address);

        order = basket.setShippingAddress(address).update();
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertEquals(order.shippingAddressId, address.getId().intValue());
    }

    @Test
    public void testBilling() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        Address address = client.addresses().first().data();
        junit.framework.Assert.assertNotNull(address);

        junit.framework.Assert.assertNotNull(basket);

        order = basket.setBillingAddress(address).update();
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertEquals(order.billingAddressId, address.getId().intValue());
    }

    @Test
    public void testPayment() throws EntityNotFoundException, IOException, ServiceException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Order order = basket.add(new BasketPredicate(product.getId(), 2));

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        PaymentMethod paymentMethod = client.paymentMethods().first().data();
        junit.framework.Assert.assertNotNull(paymentMethod);

        junit.framework.Assert.assertNotNull(basket);

        order = basket.setPaymentMethod(paymentMethod.getId()).update();
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertEquals(order.paymentMethodId, paymentMethod.getId().intValue());
    }
}
