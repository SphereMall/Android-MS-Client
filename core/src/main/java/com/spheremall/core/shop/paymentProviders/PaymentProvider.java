package com.spheremall.core.shop.paymentProviders;

import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.exceptions.SphereMallException;

import java.io.IOException;
import java.util.Map;

public interface PaymentProvider {

    Map<String, String> getPaymentForm(Order order);

    void paymentCallback() throws IOException, SphereMallException;
}
