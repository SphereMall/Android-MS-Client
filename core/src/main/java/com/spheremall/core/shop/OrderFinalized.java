package com.spheremall.core.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.OrderItem;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrderFinalized {

    protected static final int DEFAULT_ORDER_ID = -1;

    public int subTotalVatPrice;
    public int totalVatPrice;
    public int subTotalPrice;
    public int totalPrice;
    public int totalPriceWithoutDelivery;
    public List<OrderItem> items;

    protected int id = DEFAULT_ORDER_ID;
    protected int paymentMethod;
    protected int statusId;
    protected int paymentStatusId;
    protected String orderId;
    protected Delivery delivery;
    protected Address shippingAddress;
    protected Address billingAddress;
    protected User user;

    protected SMClient client;

    public OrderFinalized(SMClient client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public User getUser() {
        return user;
    }

    public SMClient getClient() {
        return client;
    }

    public void setOrderData(Order order) {
        this.id = order.getId();
        setProperties(order);
    }

    protected void setProperties(Order order) {
        this.orderId = order.orderId;
        this.statusId = order.statusId;
        this.paymentStatusId = order.paymentStatusId;

        this.items = order.items;

        this.subTotalVatPrice = order.subTotalVatPrice;
        this.totalVatPrice = order.totalVatPrice;
        this.subTotalPrice = order.subTotalPrice;
        this.totalPrice = order.totalPrice;
        this.totalPriceWithoutDelivery = order.totalPrice;

        this.paymentMethod = order.paymentMethodId;
    }

    public void update(HashMap<String, String> params) throws EntityNotFoundException, ServiceException, IOException {
        Order order = client.orders().update(id, params).data();
        OrderFinalized orderWithItems = client.orders().byId(id);
        order.items = orderWithItems.items;
        setOrderData(order);
    }
}
