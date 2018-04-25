package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("paymentProviders")
public class PaymentProvider extends Entity {

    public String title;
    public String className;
    public MSUrl msUrl;
    public String merchantId;
    public String postUrl;
    public String secretKey;
    public String keyVersion;
    public String apiKey;
    public String shaIn;
    public String shaOut;
    public String autoReturnUrl;
    public MSUrl returnUrl;

    public static class MSUrl {
        public String url;
        public Action action;

        public static class Action {
            public String type;
            public String objectId;
        }
    }
}
