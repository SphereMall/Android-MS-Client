package com.spheremall.core.resources.price;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.price.PriceValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface ProductPriceConfigurationsResource extends Resource<ProductPriceConfigurationsResource, PriceValue> {

    Response<List<PriceValue>> findPrice(PriceConfigurationFilter priceConfigurationFilter) throws JSONException, SphereMallException, IOException;

    PriceValue findProductPrice(PriceProduct priceProduct) throws JSONException, SphereMallException, IOException;
}
