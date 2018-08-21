package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.shop.ProductsToPromotions;
import com.spheremall.core.entities.shop.Promotion;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.Maker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.resources.price.PriceProduct;
import com.spheremall.core.resources.price.ProductPriceConfigurationFilter;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class PromotionsResourceImpl extends BaseResource<Promotion, PromotionsResource> implements PromotionsResource {

    public PromotionsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public PromotionsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "promotions";
    }

    @Override
    public ObjectMaker<Promotion> initializeMaker() {
        return new ObjectMaker<>(Promotion.class);
    }

    @Override
    protected PromotionsResource currentContext() {
        return this;
    }

    @Override
    public ProductsToPromotions price(PriceProduct priceProduct) throws JSONException, SphereMallException, IOException {
        ProductPriceConfigurationFilter filter = new ProductPriceConfigurationFilter();
        filter.addProduct(priceProduct);
        String data = filter.getData();
        HashMap<String, String> params = new HashMap<>();
        params.put("products", data);

        ResponseMonada responseMonada = request.handle(Method.POST, "products/byids", params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Maker<ProductsToPromotions> maker = new ObjectMaker<>(ProductsToPromotions.class);

        return maker.makeSingle(responseMonada.getResponse()).data();
    }
}
