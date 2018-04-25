package com.spheremall.core.resources.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserResource extends Resource<UserResource, User> {
    User subscribe(String email, String name) throws SphereMallException, IOException;

    User unSubscribe(String guId) throws SphereMallException, IOException;

    List<Entity> getWishList(int userId) throws SphereMallException, IOException;

    Entity addToWishList(int userId, int objectId, String entity) throws SphereMallException, IOException;

    boolean removeFromWishList(int userId, int objectId, String entity) throws SphereMallException, IOException;

    Response<Address> setAddress(int addressId, int userId, HashMap<String, String> params) throws SphereMallException, IOException;

    User get(String deviceId) throws SphereMallException, IOException;
}
