package com.spheremall.core.resources.price;

import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductPriceConfigurationFilter extends PriceConfigurationFilter {

    @Override
    public String getData() throws SphereMallException, JSONException {
        if (priceProducts.isEmpty()) {
            throw new SphereMallException("Property products is empty. Add at least one product for filtering");
        }

        JSONArray priceJsonData = new JSONArray();

        for (PriceProduct priceProduct : priceProducts) {
            JSONObject rowData = new JSONObject();
            rowData.put("priceTypeId", priceProduct.priceTypeId);
            rowData.put("productId", priceProduct.productId);

            if (!priceProduct.attributes.isEmpty()) {
                JSONArray affectAttributes = new JSONArray();

                for (AttributeValue attribute : priceProduct.attributes) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("attributeId", attribute.attributeId);
                    jsonObject.put("attributeValueId", attribute.getId());
                    affectAttributes.put(jsonObject);
                }

                rowData.put("attributes", affectAttributes);
            }
            priceJsonData.put(rowData);
        }

        return priceJsonData.toString();
    }
}
