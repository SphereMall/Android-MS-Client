package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Currency;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class CurrenciesResourceImpl extends BaseResource<Currency, CurrenciesResource> implements CurrenciesResource {

    public CurrenciesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public CurrenciesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "currencies";
    }

    @Override
    public ObjectMaker<Currency> initializeMaker() {
        return new ObjectMaker<>(Currency.class);
    }

    @Override
    protected CurrenciesResource currentContext() {
        return this;
    }
}
