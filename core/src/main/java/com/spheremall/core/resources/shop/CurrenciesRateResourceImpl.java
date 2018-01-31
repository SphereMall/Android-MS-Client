package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.CurrencyRate;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class CurrenciesRateResourceImpl extends BaseResource<CurrencyRate, CurrenciesRateResource> implements CurrenciesRateResource {

    public CurrenciesRateResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public CurrenciesRateResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "currenciesrate";
    }

    @Override
    public ObjectMaker<CurrencyRate> initializeMaker() {
        return new ObjectMaker<>(CurrencyRate.class);
    }

    @Override
    protected CurrenciesRateResource currentContext() {
        return this;
    }
}
