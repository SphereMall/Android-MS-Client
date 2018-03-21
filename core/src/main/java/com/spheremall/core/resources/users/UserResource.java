package com.spheremall.core.resources.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserResource extends Resource<UserResource, User> {
    User subscribe(String email, String name) throws EntityNotFoundException, IOException, ServiceException;

    User unSubscribe(String guId) throws EntityNotFoundException, IOException, ServiceException;

    List<Entity> getWishList(int userId) throws EntityNotFoundException, IOException, ServiceException;

    Entity addToWishList(int userId, int objectId, String entity) throws EntityNotFoundException, ServiceException, IOException;

    boolean removeFromWishList(int userId, int objectId, String entity) throws EntityNotFoundException, IOException, ServiceException;

    Response<Address> setAddress(int addressId, int userId, HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException;

    User get(String deviceId) throws EntityNotFoundException, IOException, ServiceException;
}
