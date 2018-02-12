package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.Maker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.specifications.users.IsUserEmail;
import com.spheremall.core.specifications.users.IsUserSubscriber;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserResourceImpl extends BaseResource<User, UserResource> implements UserResource {

    public UserResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "users";
    }

    @Override
    public ObjectMaker<User> initializeMaker() {
        return new ObjectMaker<>(User.class);
    }

    @Override
    protected UserResource currentContext() {
        return this;
    }

    @Override
    public User subscribe(String email, String name) throws EntityNotFoundException, IOException, ServiceException {
        User user;
        try {
            user = filters(new IsUserEmail(email)).first().data();
        } catch (EntityNotFoundException e) {
            user = new User();
            user.email = email;
            user.name = name;
            user.guid = UUID.randomUUID().toString();
            user.isSubscriber = 1;
        }

        if (new IsUserSubscriber().isSatisfied(user)) {
            return user;
        }

        HashMap<String, String> params = new HashMap<>();

        params.put("isSubscriber", "1");
        params.put("email", user.email);
        params.put("name", user.name);
        params.put("guid", user.guid);

        if (user.getId() != 0) {
            return update(user.getId(), params).data();
        }

        return create(params).data();
    }

    @Override
    public User unSubscribe(String guId) throws EntityNotFoundException, IOException, ServiceException {
        User user;
        try {
            user = filters(new Predicate("guid", FilterOperators.EQUAL, guId)).first().data();
            HashMap<String, String> params = new HashMap<>();
            params.put("isSubscriber", "0");
            return update(user.getId(), params).data();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Entity> getWishList(int userId) throws EntityNotFoundException, IOException, ServiceException {
        ResponseMonada responseMonada = request.handle(Method.GET, "wishlist/" + userId, new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        Maker<Entity> maker = new GridMaker(Entity.class);
        return maker.makeAsList(responseMonada.getResponse()).data();
    }

    @Override
    public Entity addToWishList(int userId, int objectId, String entity) throws EntityNotFoundException, ServiceException, IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("objectId", String.valueOf(objectId));
        params.put("entity", entity);

        ResponseMonada responseMonada = request.handle(Method.POST, "wishlist/" + userId, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        Maker<Entity> maker = new GridMaker(Entity.class);
        List<Entity> entities = maker.makeAsList(responseMonada.getResponse()).data();
        if (entities.size() > 0) {
            return entities.get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public boolean removeFromWishList(int userId, int objectId, String entity) {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", String.valueOf(userId));
            params.put("objectId", String.valueOf(objectId));
            params.put("entity", entity);

            ResponseMonada responseMonada = request.handle(Method.DELETE, "wishlist/" + userId, params);
            if (responseMonada.hasError()) {
                throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
            }
            return true;
        } catch (Throwable error) {
            return false;
        }
    }

    @Override
    public Response<Address> setAddress(int addressId, int userId, HashMap<String, String> params) throws EntityNotFoundException, IOException, ServiceException {
        Response<Address> response;
        if (addressId == 0) {
            params.put("userId", String.valueOf(userId));
            response = smClient.addresses().create(params);
            HashMap<String, String> userParams = new HashMap<>();
            userParams.put("defaultAddressId", String.valueOf(response.data().getId()));
            smClient.users().update(userId, userParams);
        } else {
            response = smClient.addresses().update(addressId, params);
        }
        return response;
    }
}
