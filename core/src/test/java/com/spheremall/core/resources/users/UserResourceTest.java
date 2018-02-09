package com.spheremall.core.resources.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserResourceTest extends SetUpResourceTest {

    @Test
    public void testGetUsersList() throws EntityNotFoundException, IOException, ServiceException {
        List<User> users = client.users().all().data();
        Assert.assertNotNull(users);
        Assert.assertEquals(10, users.size());
    }

    @Test
    public void testIsUserSubscriber() throws EntityNotFoundException, IOException, ServiceException {

        try {
            User user = client.users()
                    .filters(new Predicate("email", FilterOperators.EQUAL, "v.chernetsky@spheremall.com"))
                    .first().data();
            if (user != null) {
                client.users().delete(user.getId());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("email", "v.chernetsky@spheremall.com");
        params.put("name", "Vlad");
        params.put("surname", "Chernetsky");
        client.users().create(params);

        User user = client.users()
                .filters(new Predicate("email", FilterOperators.EQUAL, "v.chernetsky@spheremall.com"))
                .first().data();

        params.clear();
        params.put("isSubscriber", "1");
        user = client.users().update(user.getId(), params).data();
        Assert.assertEquals(1, user.isSubscriber);

        params.clear();
        params.put("isSubscriber", "0");
        user = client.users().update(user.getId(), params).data();
        Assert.assertEquals(0, user.isSubscriber);

        Assert.assertTrue(client.users().delete(user.getId()));
    }

    @Test
    public void testWishList() throws EntityNotFoundException, ServiceException, IOException {
        int userId = 5;
        Product product = client.products().first().data();
        List<Entity> wishList = client.users().getWishList(userId);
        for (Entity wishListItem : wishList) {
            if (wishListItem instanceof Product) {
                Product productWishListItem = (Product) wishListItem;
                if (productWishListItem.getId().equals(product.getId())) {
                    client.users()
                            .removeFromWishList(userId, productWishListItem.getId(), "products");
                }
            }
        }

        Entity wishListItem = client.users().addToWishList(userId, product.getId(), "products");

        List<Entity> all = client.users().getWishList(userId);
        Assert.assertTrue(all.size() > 0);
        Assert.assertEquals(product.getId(), wishListItem.getId());

        Assert.assertTrue(client.users().removeFromWishList(userId, product.getId(), "products"));
    }
}