package com.spheremall.core.resources.users;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class WishListItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        WishListItem item = client.wishListItems().first().data();

        Assert.assertNotNull(item);
        Assert.assertEquals(WishListItem.class.getSimpleName(), item.getClass().getSimpleName());
    }

    @Test
    public void testAddToWishList() throws SphereMallException, IOException {
        User user = client.users().first().data();
        Product product = client.products().first().data();
        List<WishListItem> wishList = client.wishListItems().all().data();
        for (WishListItem wishListItem : wishList) {
            if (wishListItem.objectId == product.getId()) {
                client.wishListItems().delete(wishListItem.getId());
            }
        }

        WishListItem wishListItem = client.wishListItems().addToWishList(user.getId(), product.getId(), 1);

        List<WishListItem> all = client.wishListItems().all().data();
        Assert.assertTrue(all.size() > 0);
        Assert.assertEquals(product.getId().intValue(), wishListItem.objectId);
        Assert.assertTrue(client.wishListItems().delete(wishListItem.getId()));
    }

    @Test
    public void testAddToWishListWithEntityString() throws SphereMallException, IOException {
        int userId = client.users().first().data().getId();

        Product product = client.products().first().data();
        List<WishListItem> wishList = client.wishListItems().all().data();
        for (WishListItem wishListItem : wishList) {
            if (wishListItem.objectId == product.getId()) {
                client.wishListItems().delete(wishListItem.getId());
            }
        }

        WishListItem wishListItem = client.wishListItems().addToWishList(userId, product.getId(), "products");

        List<WishListItem> all = client.wishListItems().all().data();
        Assert.assertTrue(all.size() > 0);
        Assert.assertEquals(product.getId().intValue(), wishListItem.objectId);

        List<WishListItem> entities = client.wishListItems().getWishList(userId, 100, 0);
        Assert.assertNotNull(entities);
        Assert.assertTrue(entities.size() > 0);

        Assert.assertTrue(client.wishListItems().delete(wishListItem.getId()));
    }

    @Test
    public void testGetWithList() throws IOException, SphereMallException {
        int userId = client.users().first().data().getId();
        List<WishListItem> entities = client.wishListItems().getWishList(3, 100, 0);
        Assert.assertNotNull(entities);
    }
}
