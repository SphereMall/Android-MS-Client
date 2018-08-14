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

    public Basket(SMClient client) throws SphereMallException, IOException {
        super(client);
        basketCreate();
    }

    public Basket(SMClient client, int basketId) throws SphereMallException, IOException {
        super(client);
        this.id = basketId;
        this.get(this.id);
    }

    public Basket(SMClient client, int basketId, int userId) throws SphereMallException, IOException {
        super(client);
        if (basketId == DEFAULT_ORDER_ID) {
            this.getByUserId(userId);
        } else {
            this.get(basketId);
        }
    }

    public SMClient getClient() {
        return client;
    }

    public Order add(BasketPredicate... predicates) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }

        HashMap<String, String> params = queryParams(Arrays.asList(predicates));
        Order order = client.basketResource().create(params).data();
        setOrderData(order);
        return order;
    }

    public Order remove(BasketPredicate... predicates) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            throw new IllegalArgumentException("Can not delete items. Shop is not created.");
        }

        HashMap<String, String> params = queryParams(Arrays.asList(predicates));

        Order order = client.basketResource().removeItems(params);
        setOrderData(order);
        return order;
    }

    public Order update(BasketPredicate... predicates) throws SphereMallException, IOException {
        if (id == DEFAULT_ORDER_ID) {
            basketCreate();
        }
        HashMap<String, String> params = queryParams(Arrays.asList(predicates));
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

    protected HashMap<String, String> queryParams(List<BasketPredicate> predicates) {
        HashMap<String, String> params = new HashMap<>();

        params.put("basketId", String.valueOf(getId()));

        JSONArray jsonArray = new JSONArray();
        for (BasketPredicate predicate : predicates) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", predicate.id);
                int itemId = getItemIdByProduct(predicate.id);

                if (itemId > 0) {
                    jsonObject.put("itemId", getItemIdByProduct(predicate.id));
                }

                if (predicate.amount != 0) {
                    jsonObject.put("amount", predicate.amount);
                }
                if (predicate.compound != null) {
                    jsonObject.put("compound", predicate.compound);
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

    protected int getItemIdByProduct(int productId) {
        for (OrderItem order : getItems()) {
            if (order.productId == productId) {
                return order.getId();
            }
        }
        return 0;
    }
}