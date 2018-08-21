package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.ProductsToPromotions;
import com.spheremall.core.entities.shop.Promotion;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;
import com.spheremall.core.resources.price.PriceProduct;

import org.json.JSONException;

import java.io.IOException;

public interface PromotionsResource extends Resource<PromotionsResource, Promotion> {

    ProductsToPromotions price(PriceProduct priceProduct) throws JSONException, SphereMallException, IOException;
}
