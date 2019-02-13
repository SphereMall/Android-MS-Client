package com.spheremall.core.filters.elasticsearch.facets.configs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ESPriceRangeConfig implements ESCatalogConfig {

    private final List<String> attrCodes = new ArrayList<>();
    private final List<String> fields = new ArrayList<>();

    private ESPriceRangeConfig() {
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String name() {
        return "range";
    }

    @Override
    public JSONObject toConfig() {
        JSONArray attrArray = new JSONArray();
        JSONArray fieldsArray = new JSONArray();
        for (String code : attrCodes) attrArray.put(code);
        for (String field : fields) fieldsArray.put(field);

        JSONObject range = new JSONObject();
        try {
            range.put("attributes", attrArray);
            range.put("fields", fields);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return range;
    }

    public static class Builder {

        private final List<String> attrCodes = new ArrayList<>();
        private final List<String> fields = new ArrayList<>();

        public Builder addAttrCodes(String... codes) {
            this.attrCodes.addAll(Arrays.asList(codes));
            return this;
        }

        public Builder addFields(String... fields) {
            this.fields.addAll(Arrays.asList(fields));
            return this;
        }

        public ESPriceRangeConfig create() {
            ESPriceRangeConfig config = new ESPriceRangeConfig();
            config.attrCodes.addAll(attrCodes);
            config.fields.addAll(fields);
            return config;
        }
    }
}
