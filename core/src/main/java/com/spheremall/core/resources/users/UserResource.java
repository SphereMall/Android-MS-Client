package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.List;

public interface UserResource extends Resource<UserResource, User> {
    User subscribe(String email, String name) throws EntityNotFoundException, IOException, ServiceException;

    User unSubscribe(String guId) throws EntityNotFoundException, IOException, ServiceException;

    List<WishListItem> getWishList(int userId) throws EntityNotFoundException, IOException, ServiceException;

    WishListItem addToWishList(int userId, int productId) throws EntityNotFoundException, ServiceException, IOException;

    boolean removeFromWishList(int userId, int productId) throws EntityNotFoundException, IOException, ServiceException;
}
