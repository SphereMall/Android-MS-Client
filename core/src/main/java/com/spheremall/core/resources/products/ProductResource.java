package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.full.FullResource;

import java.io.IOException;
import java.util.List;

public interface ProductResource extends FullResource<Product, ProductResource> {

    Product detail(int id) throws IOException, SphereMallException;

    List<Product> detail() throws IOException, SphereMallException;
}
