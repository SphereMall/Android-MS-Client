package com.spheremall.core.filters.grid;

import android.util.Log;

import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GridFilter extends Filter {

    protected List<List<GridFilterElement>> elements = new ArrayList<>();

    public GridFilter elements(GridFilterElement... elements) {
        List<GridFilterElement> levelElements = new ArrayList<>(Arrays.asList(elements));
        this.elements.add(levelElements);
        return this;
    }

    public GridFilter reset() {
        elements.clear();
        return this;
    }

    public GridFilter setFilters(List<Predicate> predicates) {
        for (Predicate item : predicates) {
            this.filters.add(new Predicate(item.field, null, item.value));
        }
        return this;
    }

    public GridFilter setFilters(Predicate... predicates) {
        for (Predicate item : predicates) {
            this.filters.add(new Predicate(item.field, null, item.value));
        }
        return this;
    }

    public GridFilter addFilter(String field, String value) {
        this.filters.add(new Predicate(field, null, value));
        return this;
    }

    public List<List<GridFilterElement>> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return mapParamsToString(toParams());
    }

    public HashMap<String, String> toParams() {
        HashMap<String, String> set = getStandardFilter();
        if (elements != null && elements.size() > 0) {
            JSONArray jsonParams = new JSONArray();
            for (List<GridFilterElement> levelElements : elements) {
                JSONObject conditionObject = new JSONObject();
                for (GridFilterElement element : levelElements) {
                    String key = element.name;
                    try {
                        if (conditionObject.has(key)) {
                            JSONArray array = element.asArray();
                            for (int i = 0; i < array.length(); i++) {
                                conditionObject.getJSONArray(key).put(array.get(i));
                            }
                        } else {
                            conditionObject.put(key, element.asArray());
                        }
                    } catch (JSONException e) {
                        Log.e("GridFilter", e.getLocalizedMessage());
                    }
                }
                jsonParams.put(conditionObject);
            }
            set.put("params", jsonParams.toString());
        }
        return set;
    }

    private HashMap<String, String> getStandardFilter() {
        HashMap<String, String> set = new HashMap<>();
        for (Predicate predicate : filters) {
            set.put(predicate.field, predicate.value);
        }
        return set;
    }

    private String mapParamsToString(HashMap<String, String> params) {
        Set entrySet = params.entrySet();
        Iterator iterator = entrySet.iterator();
        StringBuilder builder = new StringBuilder();

        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                builder.append("&");
            }
        }

        return builder.toString();
    }
}