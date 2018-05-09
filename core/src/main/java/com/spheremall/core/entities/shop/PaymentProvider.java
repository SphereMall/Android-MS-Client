package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("paymentProviders")
public class PaymentProvider extends Entity {

    public String title;
    public int websiteId;
    public String className;
    public String msUrl;
    public String merchantId;
    public String postUrl;
    public String secretKey;
    public String keyVersion;
    public String apiKey;
    public String shaIn;
    public String shaOut;
    public String autoReturnUrl;
    public String returnUrl;
    public int order;
}
