package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.common.LeafQueryFilter;
import com.spheremall.core.utils.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexFilter extends LeafQueryFilter {

    public final List<String> indexes;

    public IndexFilter(ArrayList<String> indexes) {
        this.indexes = indexes;
        this.name = "index";
    }

    public IndexFilter(String... indexes) {
        this.indexes = Arrays.asList(indexes);
        this.name = "index";
    }

    @Override
    public JSONObject toJson() {

        JSONObject indexObject = new JSONObject();
        try {
            if (indexes.size() == 0) {
                indexObject.put(name, "sm-*");
            } else if (indexes.size() == 1) {
                indexObject.put(name, indexes.get(0));
            } else {
                JSONArray entitiesJson = new JSONArray();
                for (String entity : indexes) {
                    entitiesJson.put(entity);
                }
                indexObject.put(name, entitiesJson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return indexObject;
    }

    @Override
    public String toString() {
        return TextUtils.join(",", indexes);
    }
}
