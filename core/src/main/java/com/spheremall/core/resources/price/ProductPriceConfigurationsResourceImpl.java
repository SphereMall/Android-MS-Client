package com.spheremall.core.resources.price;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.price.PriceValue;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.makers.ProductPriceMaker;
import com.spheremall.core.resources.BaseResource;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProductPriceConfigurationsResourceImpl extends BaseResource<PriceValue, ProductPriceConfigurationsResource> implements ProductPriceConfigurationsResource {

    public ProductPriceConfigurationsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public ProductPriceConfigurationsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "findprice";
    }

    @Override
    public ObjectMaker<PriceValue> initializeMaker() {
        return new ProductPriceMaker(PriceValue.class);
    }

    @Override
    protected ProductPriceConfigurationsResource currentContext() {
        return this;
    }

    @Override
    public Response<List<PriceValue>> findPrice(PriceConfigurationFilter priceConfigurationFilter) throws JSONException, SphereMallException, IOException {
        String data = priceConfigurationFilter.getData();
        HashMap<String, String> params = new HashMap<>();
        params.put("filters", data);
        ResponseMonada responseMonada = request.handle(Method.POST, "", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public PriceValue findProductPrice(PriceProduct priceProduct) throws JSONException, SphereMallException, IOException {
        PriceConfigurationFilter filter = new PriceConfigurationFilter();
        filter.addProduct(priceProduct);
        String data = filter.getData();
        HashMap<String, String> params = new HashMap<>();
        params.put("filters", data);
        ResponseMonada responseMonada = request.handle(Method.POST, "", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        return maker.makeSingle(responseMonada.getResponse()).data();
    }

    @Override
    public Response<PriceValue> create(HashMap<String, String> params) {
        throw new MethodNotFoundException("Method create() can not be use with product price configurations");
    }

    @Override
    public Response<PriceValue> get(int id) {
        throw new MethodNotFoundException("Method get() can not be use with product price configurations");
    }

    @Override
    public Response<PriceValue> update(Integer id, HashMap<String, String> params) {
        throw new MethodNotFoundException("Method update() can not be use with product price configurations");
    }

    @Override
    public Boolean delete(Integer id) {
        throw new MethodNotFoundException("Method delete() can not be use with product price configurations");
    }
}
