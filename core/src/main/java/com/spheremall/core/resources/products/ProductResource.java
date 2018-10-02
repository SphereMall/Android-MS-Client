package com.spheremall.core.resources.products;

import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.full.FullResource;

import java.io.IOException;
import java.util.List;

public interface ProductResource extends FullResource<Product, ProductResource> {

    Response<List<Attribute>> variants(String... codes) throws IOException, SphereMallException;

    Product detail(int id) throws IOException, SphereMallException;

    Response<List<Product>> detail() throws IOException, SphereMallException;
}
