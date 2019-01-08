package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductVariant;
import com.spheremall.core.entities.products.ProductVariantsContainer;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.utils.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductResourceImpl extends BaseResource<Product, ProductResource> implements ProductResource {

    protected ProductAttributesBuilder attributesBuilder = new ProductAttributesBuilder();

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
    public List<ProductVariantsContainer> variants(List<Integer> productIds, List<String> attributeCodes) throws IOException, SphereMallException {
        ArrayUtils<Integer> arrayUtils = new ArrayUtils<>();

        List<ProductVariant> productVariants = smClient.productVariants()
                .in("productId", arrayUtils.numericListToStringArray(productIds))
                .limit(100)
                .all()
                .data();

        ProductVariantsBuilder variantsBuilder = new ProductVariantsBuilder(smClient, attributesBuilder);

        return variantsBuilder.build(attributeCodes, productVariants);
    }

    @Override
    public Product detail(int id) throws IOException, SphereMallException {
        String urlAppend = "detail/" + id;
        return getProduct(urlAppend);
    }

    @Override
    public Product detail(String urlCode) throws SphereMallException, IOException {
        String urlAppend = "detail/url/" + urlCode;
        return getProduct(urlAppend);
    }

    private Product getProduct(String urlAppend) throws SphereMallException, IOException {
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Product product = maker.makeSingle(responseMonada.getResponse()).data();

        return attributesBuilder.combineProductProperties(product);
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
            productsResponse.add(attributesBuilder.combineProductProperties(product));
        }

        return new Response<>(productsResponse, listResponse.meta());
    }

    @Override
    public Response<List<Product>> variants(List<Integer> productIds) throws IOException, SphereMallException, CloneNotSupportedException {
        String urlAppend = "detail/variants";
        ids(productIds);
        limit(100);
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, super.getQueryParams());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Response<List<Product>> listResponse = maker.makeAsList(responseMonada.getResponse());

        List<Product> data = listResponse.data();

        List<Product> productsResponse = new ArrayList<>();
        for (Product product : data) {
            Product changedProduct = attributesBuilder.combineProductProperties(product);
            productsResponse.add(changedProduct);
        }

        System.out.println(productsResponse);

        return new Response<>(productsResponse, listResponse.meta());
    }
}
