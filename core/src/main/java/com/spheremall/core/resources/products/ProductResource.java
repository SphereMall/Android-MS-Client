package com.spheremall.core.resources.products;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductVariantsContainer;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.full.DetailResource;

import java.io.IOException;
import java.util.List;

public interface ProductResource extends DetailResource<Product, ProductResource> {

    List<ProductVariantsContainer> variants(List<Integer> productIds, List<String> attributeCodes) throws IOException, SphereMallException;

    Response<List<Product>> variants(List<Integer> productIds) throws IOException, SphereMallException;

    Product detail(int id) throws IOException, SphereMallException;

    Response<List<Product>> detail() throws IOException, SphereMallException;
}
