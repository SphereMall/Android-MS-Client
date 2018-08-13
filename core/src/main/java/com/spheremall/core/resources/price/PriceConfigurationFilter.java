package com.spheremall.core.resources.price;

import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PriceConfigurationFilter {

    private List<PriceProduct> priceProducts = new ArrayList<>();

    public void addProduct(PriceProduct priceProduct) {
        priceProducts.add(priceProduct);
    }

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
                JSONObject attrObject = new JSONObject();
                JSONArray affectAttrObject = new JSONArray();
                JSONArray valuesObject = new JSONArray();

                for (AttributeValue attribute : priceProduct.attributes) {
                    affectAttrObject.put(attribute.attributeId);
                    valuesObject.put(attribute.getId());
                }


                JSONArray array = new JSONArray();
                array.put(valuesObject);
                attrObject.put("affectAttributes", affectAttrObject);
                attrObject.put("values", array);
                rowData.put("attributes", attrObject);
            }
            priceJsonData.put(rowData);
        }

        return priceJsonData.toString();
    }
}
