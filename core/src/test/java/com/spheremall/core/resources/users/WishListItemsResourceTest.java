package com.spheremall.core.resources.users;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class WishListItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        WishListItem item = client.wishListItems().first().data();

        Assert.assertNotNull(item);
        Assert.assertEquals(WishListItem.class.getSimpleName(), item.getClass().getSimpleName());
    }

    @Test
    public void testAddToWishList() throws EntityNotFoundException, ServiceException, IOException {
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
    public void testAddToWishListWithEntityString() throws EntityNotFoundException, ServiceException, IOException {
        int userId = 5;
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
        Assert.assertTrue(client.wishListItems().delete(wishListItem.getId()));
    }

    @Test
    public void testGetWishListEntities() throws EntityNotFoundException, ServiceException, IOException {
        int userId = 5;
        List<WishListItem> entities = client.wishListItems().getWishList(userId, 100, 0);
        Assert.assertNotNull(entities);
        Assert.assertTrue(entities.size() > 0);
    }
}
