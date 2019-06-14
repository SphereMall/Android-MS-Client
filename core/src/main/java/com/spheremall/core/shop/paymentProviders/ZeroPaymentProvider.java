package com.spheremall.core.shop.paymentProviders;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.shop.OrderStatus;
import com.spheremall.core.shop.PaymentStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZeroPaymentProvider implements PaymentProvider {

    public final SMClient client;
    public final int basketId;

    public ZeroPaymentProvider(SMClient client, int basketId) {
        this.client = client;
        this.basketId = basketId;
    }

    @Override
    public Map<String, String> getPaymentForm(Order order) {
        return new HashMap<>();
    }

    @Override
    public void paymentCallback() throws IOException, SphereMallException {
        HashMap<String, String> params = new HashMap<>();
        params.put("statusId", OrderStatus.NEW_ORDER);
        params.put("paymentStatusId", PaymentStatus.IN_PROGRESS);
        params.put("orderId", "MOB_00" + basketId);
        client.orders().update(basketId, params);
        client.clearBasket();
    }
}
