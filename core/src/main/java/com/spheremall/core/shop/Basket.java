package com.spheremall.core.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Basket extends OrderFinalized {

    protected HashMap<String, String> updateParams = new HashMap<>();

    public Basket(SMClient client) {
        super(client);
    }

    public Basket(SMClient client, int id) throws EntityNotFoundException, ServiceException, IOException {
        super(client);
        this.id = id;
        this.get(this.id);
    }

    public SMClient getClient() {
        return client;
    }

    public void add(BasketPredicate... predicates) throws EntityNotFoundException, IOException, ServiceException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }

        HashMap<String, String> params = queryParams(Arrays.asList(predicates));
        Order order = client.basketResource().create(params).data();
        setProperties(order);
    }

    public void remove(BasketPredicate... predicates) throws EntityNotFoundException, ServiceException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            throw new IllegalArgumentException("Can not delete items. Shop is not created.");
        }

        HashMap<String, String> params = queryParams(Arrays.asList(predicates));

        Order order = client.basketResource().removeItems(params);
        setProperties(order);
    }

    public void update(BasketPredicate... predicates) throws EntityNotFoundException, IOException, ServiceException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }
        HashMap<String, String> params = queryParams(Arrays.asList(predicates));
        params.putAll(updateParams);
        Order order = client.basketResource().update(id, params).data();
        setProperties(order);
        updateParams.clear();
    }

    public Basket setDelivery(Delivery delivery) {
        if (delivery.id == 0) {
            throw new IllegalArgumentException("Can't set delivery. Delivery id is empty.");
        }

        updateParams.put("deliveryProviderId", String.valueOf(delivery.id));
        updateParams.put("deliveryCost", String.valueOf(delivery.getCost()));
        return this;
    }

    public Basket setPaymentMethod(String paymentMethod) {
        updateParams.put("paymentMethodId", paymentMethod);
        return this;
    }

    public Basket setBillingAddress(Address address) throws EntityNotFoundException, IOException, ServiceException {
        setAddress(address, "billingAddress");
        return this;
    }

    public Basket setShippingAddress(Address address) throws EntityNotFoundException, IOException, ServiceException {
        setAddress(address, "shippingAddress");
        return this;
    }

    public Basket setUser(User user) {
        if (user.getId() == 0) {
            throw new IllegalArgumentException("Can't set user. User id is empty.");
        }

        updateParams.put("userId", String.valueOf(user.getId()));
        return this;
    }

    protected void basketCreate() throws EntityNotFoundException, ServiceException, IOException {
        Order order = client.basketResource().createNew();
        this.id = order.getId();
    }

    protected void get(int id) throws EntityNotFoundException, IOException, ServiceException {
        if (id != DEFAULT_ORDER_ID) {

            Order order = client.basketResource().get(id).data();

            if (order == null) {
                throw new EntityNotFoundException();
            }

            this.setProperties(order);
        }
    }

    protected void setAddress(Address address, String addressKey) throws EntityNotFoundException, ServiceException, IOException {
        if (address.getId() == 0) {
            Address newAddress = client.addresses()
                    .create(address.asParams()).data();

            updateParams.put(addressKey + "Id", newAddress.getId().toString());
        }
    }

    protected HashMap<String, String> queryParams(List<BasketPredicate> predicates) {
        HashMap<String, String> params = new HashMap<>();

        params.put("basketId", String.valueOf(getId()));

        JSONArray jsonArray = new JSONArray();
        for (BasketPredicate predicate : predicates) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", predicate.id);

                if (predicate.amount != 0) {
                    jsonObject.put("amount", predicate.amount);
                }
                if (predicate.compound != null) {
                    jsonObject.put("compound", predicate.compound);
                }
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        params.put("products", jsonArray.toString());
        return params;
    }
}