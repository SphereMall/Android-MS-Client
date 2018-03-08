package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface WishListItemsResource extends Resource<WishListItemsResource, WishListItem> {

    WishListItem addToWishList(int userId, int objectId, String entity) throws EntityNotFoundException, ServiceException, IOException;

    WishListItem addToWishList(int userId, int objectId, int entityId) throws EntityNotFoundException, ServiceException, IOException;

    List<WishListItem> getWishList(int userId, int limit, int offset) throws EntityNotFoundException, IOException, ServiceException;
}
