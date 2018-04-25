package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface WishListItemsResource extends Resource<WishListItemsResource, WishListItem> {

    WishListItem addToWishList(int userId, int objectId, String entity) throws SphereMallException, IOException;

    WishListItem addToWishList(int userId, int objectId, int entityId) throws SphereMallException, IOException;

    List<WishListItem> getWishList(int userId, int limit, int offset) throws SphereMallException, IOException;
}
