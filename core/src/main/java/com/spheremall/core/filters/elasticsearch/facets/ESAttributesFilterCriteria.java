package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.facets.models.ESAttributeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ESAttributesFilterCriteria implements ESCatalogFilterCriteria {

    private final HashMap<String, ArrayList<String>> attributes = new HashMap<>();

    public final String code;
    public final List<String> values = new ArrayList<>();

    public ESAttributesFilterCriteria(String code, String... value) {
        this.code = code;
        this.values.addAll(Arrays.asList(value));
    }

    @Override
    public String name() {
        return "attributes";
    }

    @Override
    public String toJson() throws SphereMallException {
//        try {
//            JSONObject object = new JSONObject();
//            HashSet<String> processedAttrCodes = new HashSet<>();
//
//            for (ESAttributeModel attribute : code) {
//                if (processedAttrCodes.contains(attribute.code)) continue;
//
//                processedAttrCodes.add(attribute.code);
//
//                JSONArray attrsArray = new JSONArray();
//                for (ESAttributeModel nestedAttr : attributes) {
//                    if (attribute.code.equals(nestedAttr.code)) {
//                        attrsArray.put(nestedAttr.value);
//                    }
//                }
//                JSONObject value = new JSONObject();
//                value.put("value", attrsArray);
//                object.put(attribute.code, value);
//            }
//            return object;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            throw new SphereMallException(e);

        //{attributes:{"reward":{"value":["0","1"]}}}
//        }
        return "{\"reward\":{\"value\":[\"0\",\"1\"]}}";
    }
}
