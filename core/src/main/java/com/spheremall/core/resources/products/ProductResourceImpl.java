package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.price.PriceConfiguration;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.full.FullResourceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductResourceImpl extends FullResourceImpl<Product, ProductResource> implements ProductResource {

    public ProductResourceImpl(SMClient client) {
        super(client);
    }

    @Override
    public String getURI() {
        return "products";
    }

    @Override
    public ObjectMaker<Product> initializeMaker() {
        return new ObjectMaker<>(Product.class);
    }

    @Override
    protected ProductResource currentContext() {
        return this;
    }

    @Override
    public Product detail(int id) throws IOException, SphereMallException {
        String urlAppend = "detail/" + id;
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Product product = maker.makeSingle(responseMonada.getResponse()).data();

        return combineProductProperties(product);
    }

    @Override
    public Response<List<Product>> detail() throws IOException, SphereMallException {
        String urlAppend = "detail/list";
        HashMap<String, String> params = getQueryParams();
        params.put("actions", "priceconfigurations,promotions");
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Response<List<Product>> listResponse = maker.makeAsList(responseMonada.getResponse());

        List<Product> productsResponse = new ArrayList<>();
        for (Product product : listResponse.data()) {
            productsResponse.add(combineProductProperties(product));
        }

        return new Response<>(productsResponse, listResponse.meta());
    }

    private Product combineProductProperties(Product product) {

        if (product.productPriceConfigurations == null || product.productPriceConfigurations.size() == 0) {
            mapAttributes(product);
            return product;
        }

        List<PriceConfiguration> priceConfigurations = product.productPriceConfigurations.get(0).priceConfigurations;
        if (priceConfigurations == null || priceConfigurations.size() == 0) {
            mapAttributes(product);
            return product;
        }

        List<String> affectAttributes = priceConfigurations.get(0).affectAttributes;

        if (affectAttributes == null || affectAttributes.size() == 0) {
            mapAttributes(product);
            return product;
        }

        product.affectedAttributes = new ArrayList<>();

        for (Attribute attribute : product.attributes) {

            setMapAttributeValues(product, attribute);

            for (String affectedAttr : affectAttributes) {
                if (attribute.getId().toString().equals(affectedAttr)) {
                    product.affectedAttributes.add(attribute);
                }
            }
        }

        return product;
    }

    private void mapAttributes(Product product) {
        for (Attribute attribute : product.attributes) {
            setMapAttributeValues(product, attribute);
        }
    }

    private void setMapAttributeValues(Product product, Attribute attribute) {
        attribute.attributeValues = new ArrayList<>();

        for (ProductAttributeValue productAttributeValue : product.productAttributeValues) {
            if (attribute.getId() == productAttributeValue.attributeId) {
                attribute.attributeValues.addAll(productAttributeValue.attributeValues);
            }
        }
    }
}
