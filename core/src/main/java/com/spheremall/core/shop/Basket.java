package com.spheremall.core.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.OrderItem;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Basket extends OrderFinalized {

    protected HashMap<String, String> updateParams = new HashMap<>();

    public Basket(SMClient client, String version) throws SphereMallException, IOException {
        super(client, version);
        basketCreate();
    }

    public Basket(SMClient client, int basketId, String version) throws SphereMallException, IOException {
        super(client, version);
        this.id = basketId;
        this.get(this.id);
    }

    public Basket(SMClient client, int basketId, int userId, String version) throws SphereMallException, IOException {
        super(client, version);
        if (basketId == DEFAULT_ORDER_ID) {
            this.getByUserId(userId);
        } else {
            this.get(basketId);
        }
    }

    public SMClient getClient() {
        return client;
    }

    public Order add(AddBasketPredicate... predicates) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }

        HashMap<String, String> params = queryParams(Arrays.asList(predicates));
        Order order = client.basketResource().create(params).data();
        setOrderData(order);
        return order;
    }

    public Order remove(Integer... itemIds) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            throw new IllegalArgumentException("Can not delete items. Shop is not created.");
        }

        HashMap<String, String> params = removeParams(itemIds);

        Order order = client.basketResource().removeItems(params);
        setOrderData(order);
        return order;
    }

    public Order update(UpdateBasketPredicate... predicates) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }
        HashMap<String, String> params = updateParams(Arrays.asList(predicates));
        params.putAll(updateParams);
        Order order = client.basketResource().update(id, params).data();
        setOrderData(order);
        updateParams.clear();
        return order;
    }

    public Basket setDelivery(Delivery delivery) {
        if (delivery.id == 0) {
            throw new IllegalArgumentException("Can't set delivery. Delivery id is empty.");
        }

        updateParams.put("deliveryProviderId", String.valueOf(delivery.id));
        updateParams.put("deliveryCost", String.valueOf(delivery.getCost()));
        return this;
    }

    public Basket setPaymentMethod(int paymentMethodId) {
        updateParams.put("paymentMethodId", String.valueOf(paymentMethodId));
        return this;
    }

    public Basket setBillingAddress(Address address) throws SphereMallException, IOException {
        setAddress(address, "billingAddress");
        return this;
    }

    public Basket setShippingAddress(Address address) throws SphereMallException, IOException {
        setAddress(address, "shippingAddress");
        return this;
    }

    public Basket setUser(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Can't set user. User id is empty.");
        }

        updateParams.put("userId", String.valueOf(userId));
        return this;
    }

    protected void basketCreate() throws SphereMallException, IOException {
        Order order = client.basketResource().createNew();
        setOrderData(order);
    }

    protected void get(int id) throws SphereMallException, IOException {
        if (id != DEFAULT_ORDER_ID) {

            Order order = client.basketResource().get(id).data();

            if (order == null) {
                throw new EntityNotFoundException();
            }

            this.setOrderData(order);
        }
    }

    protected void getByUserId(int userId) throws SphereMallException, IOException {
        if (userId != 0) {
            Order order = client.basketResource().getByUserId(userId).data();
            if (order == null) {
                throw new EntityNotFoundException();
            }
            this.setOrderData(order);
        }
    }

    protected void setAddress(Address address, String addressKey) throws SphereMallException, IOException {
        if (address.getId() == 0) {
            address = client.addresses()
                    .create(address.asParams()).data();
        }
        updateParams.put(addressKey + "Id", address.getId().toString());
    }

    protected HashMap<String, String> queryParams(List<AddBasketPredicate> predicates) {
        HashMap<String, String> params = new HashMap<>();

        params.put("basketId", String.valueOf(getId()));

        JSONArray jsonArray = new JSONArray();
        for (AddBasketPredicate predicate : predicates) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", predicate.id);

                if (predicate.amount != 0) {
                    jsonObject.put("amount", predicate.amount);
                }

                JSONArray attributes = new JSONArray();
                if (predicate.getAttributes() != null) {
                    for (AttributesPredicate attributesPredicate : predicate.getAttributes()) {

                        JSONObject attributeObj = new JSONObject();
                        attributeObj.put("attributeId", attributesPredicate.attributeId);
                        attributeObj.put("attributeValueId", attributesPredicate.attributeValueId);

                        if (!attributesPredicate.userValue.isEmpty()) {
                            attributeObj.put("userValue", attributesPredicate.userValue);
                        }
                        attributes.put(attributeObj);
                    }
                }
                if (attributes.length() > 0) {
                    jsonObject.put("attributes", attributes);
                }

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jsonArray.length() > 0) {
            params.put("products", jsonArray.toString());
        }
        return params;
    }

    protected HashMap<String, String> removeParams(Integer... itemIds) {
        HashMap<String, String> params = new HashMap<>();
        params.put("basketId", String.valueOf(getId()));

        JSONArray jsonArray = new JSONArray();
        for (int itemId : itemIds) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", getProductIdByItemId(itemId));
                jsonObject.put("itemId", itemId);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        params.put("products", jsonArray.toString());
        return params;
    }

    protected HashMap<String, String> updateParams(List<UpdateBasketPredicate> predicates) {
        HashMap<String, String> params = new HashMap<>();
        params.put("basketId", String.valueOf(getId()));

        JSONArray jsonArray = new JSONArray();
        for (UpdateBasketPredicate predicate : predicates) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", getProductIdByItemId(predicate.itemId));
                jsonObject.put("itemId", predicate.itemId);
                jsonObject.put("amount", predicate.amount);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jsonArray.length() > 0) {
            params.put("products", jsonArray.toString());
        }
        return params;
    }

    protected int getProductIdByItemId(int itemId) {
        for (OrderItem order : getItems()) {
            if (order.getId() == itemId) {
                return order.productId;
            }
        }
        return 0;
    }
}