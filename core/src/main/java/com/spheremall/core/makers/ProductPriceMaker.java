package com.spheremall.core.makers;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.pojo.PriceValuePojo;
import com.spheremall.core.entities.price.PriceValue;
import com.spheremall.core.jsonapi.DeserializationFeature;
import com.spheremall.core.jsonapi.JSONAPIDocument;
import com.spheremall.core.jsonapi.ResourceConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPriceMaker extends ObjectMaker<PriceValue> {

    public ProductPriceMaker(Class<PriceValue> clazz) {
        super(clazz);
    }

    @Override
    public Response<PriceValue> makeSingle(String response) {
        resourceConverter = new ResourceConverter(PriceValuePojo.class);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        JSONAPIDocument<List<PriceValuePojo>> document = resourceConverter.readDocumentCollection(response.getBytes(), PriceValuePojo.class);
        PriceValuePojo entity = PriceValuePojo.class.cast(document.get().get(0));
        PriceValue priceValue = map(entity);
        Map<String, ?> meta = document.getMeta();
        if (meta == null) {
            meta = new HashMap<>();
        }
        return new Response<>(priceValue, meta);
    }

    @Override
    public Response<List<PriceValue>> makeAsList(String response) {
        resourceConverter = new ResourceConverter(PriceValuePojo.class);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        JSONAPIDocument<List<PriceValuePojo>> document = resourceConverter.readDocumentCollection(response.getBytes(), PriceValuePojo.class);
        List<PriceValuePojo> entities = Collections.checkedList(resourceConverter.readDocumentCollection(response.getBytes(), PriceValuePojo.class).get(), PriceValuePojo.class);

        List<PriceValue> priceValues = new ArrayList<>();
        for (PriceValuePojo valuePojo : entities) {
            priceValues.add(map(valuePojo));
        }

        Map<String, ?> meta = document.getMeta();
        if (meta == null) {
            meta = new HashMap<>();
        }
        return new Response<>(priceValues, meta);
    }

    private PriceValue map(PriceValuePojo priceValuePojo) {
        PriceValue priceValue = new PriceValue();
        priceValue.setId(priceValuePojo.getId());
        priceValue.setProperties(priceValuePojo.getProperties());
        priceValue.productId = priceValuePojo.productId;
        priceValue.priceWithVat = priceValuePojo.prices.priceWithVat;
        priceValue.priceWithoutVat = priceValuePojo.prices.priceWithoutVat;
        priceValue.vatPercent = priceValuePojo.vatPercent;
        return priceValue;
    }
}
