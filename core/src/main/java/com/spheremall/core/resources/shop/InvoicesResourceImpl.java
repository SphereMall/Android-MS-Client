package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.Invoice;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class InvoicesResourceImpl extends BaseResource<Invoice, InvoicesResource> implements InvoicesResource {

    public InvoicesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public InvoicesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "invoices";
    }

    @Override
    public ObjectMaker<Invoice> initializeMaker() {
        return new ObjectMaker<>(Invoice.class);
    }

    @Override
    protected InvoicesResource currentContext() {
        return this;
    }
}
