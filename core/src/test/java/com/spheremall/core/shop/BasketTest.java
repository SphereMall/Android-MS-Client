package com.spheremall.core.shop;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.PaymentMethod;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasketTest extends SetUpResourceTest {

    private static final int userId = 5;

    @Override
    public void setUp() throws SphereMallException, IOException {
        super.setUp();
        client.clearBasket();
    }

    @Test
    public void testCreateBasket() throws SphereMallException, IOException {
        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(0, basket.getUserId());
    }

    @Test
    public void testGetByUserId() throws SphereMallException, IOException {
        User user = client.users().first().data();
        Basket basket = client.basket(Basket.DEFAULT_ORDER_ID, user.getId());
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(user.getId().intValue(), basket.getUserId());
    }

    @Test
    public void testGetByBasketId() throws SphereMallException, IOException {
        int basketId = client.basket().getId();
        Basket basket = client.basket(basketId);
        junit.framework.Assert.assertNotNull(basket);
        junit.framework.Assert.assertEquals(basketId, basket.getId());
    }

    @Test
    public void testAddItemToBasket() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket(2992);
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount > 0);
    }

    @Test
    public void testRemoveItemFromBasket() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        order = basket.remove(order.items.get(0).getId());

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNull(order.items);
    }

    @Test
    public void testUpdateItemAmountInBasket() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 2);

        order = basket.update(new UpdateBasketPredicate(order.items.get(0).getId(), 3));
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertTrue(order.items.size() == 1);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 3);

        order = basket.update(new UpdateBasketPredicate(order.items.get(0).getId(), -2));
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertTrue(order.items.size() == 1);
        junit.framework.Assert.assertTrue(order.items.get(0).amount == 1);
    }

    @Test
    public void testSetUser() throws SphereMallException, IOException {
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
    public void testDelivery() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

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
    public void testShipping() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

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
    public void testBilling() throws SphereMallException, IOException {
        List<Product> products = client.products().all().data();
        junit.framework.Assert.assertNotNull(products);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        Product product = products.get(0);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

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
    public void testPayment() throws SphereMallException, IOException {
        Product product = client.products().first().data();
        junit.framework.Assert.assertNotNull(product);

        Basket basket = client.basket();
        junit.framework.Assert.assertNotNull(basket);

        List<AttributesPredicate> predicates = new ArrayList<>();
        predicates.add(new AttributesPredicate(226, 3776, "73"));
        predicates.add(new AttributesPredicate(227, 3749, "35"));
        predicates.add(new AttributesPredicate(222, 4337));
        AddBasketPredicate basketPredicate = new AddBasketPredicate(product.getId(), 2, predicates);

        Order order = basket.add(basketPredicate);

        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertNotNull(order.items);
        junit.framework.Assert.assertTrue(order.items.size() > 0);
        junit.framework.Assert.assertEquals(product.getId().intValue(), order.items.get(0).productId);
        junit.framework.Assert.assertTrue(order.items.get(0).amount > 0);

        PaymentMethod paymentMethod = client.paymentMethods().first().data();
        junit.framework.Assert.assertNotNull(paymentMethod);

        junit.framework.Assert.assertNotNull(basket);

        order = basket.setPaymentMethod(paymentMethod.getId()).update();
        junit.framework.Assert.assertNotNull(order);
        junit.framework.Assert.assertEquals(order.paymentMethodId, paymentMethod.getId().intValue());
    }
}
