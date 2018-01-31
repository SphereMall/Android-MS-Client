package com.spheremall.core.filters.grid;

import android.util.Log;

import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GridFilter extends Filter {

    protected HashMap<String, List<Class>> elements;

    public GridFilter elements(GridFilterElement... elements) {
        this.elements = new HashMap<>();

        for (GridFilterElement element : elements) {
            this.elements.put(element.getName(), element.getValues());
        }
        return this;
    }

    public GridFilter reset() {
        elements = null;
        return this;
    }

    public GridFilter setFilters(List<Predicate> predicates) {
        for (Predicate item : predicates) {
            this.filters.add(new Predicate(item.field, null, item.value));
        }
        return this;
    }

    public GridFilter addFilter(String field, String value) {
        this.filters.add(new Predicate(field, null, value));
        return this;
    }

    public HashMap<String, List<Class>> getElements() {
        return elements;
    }

//    params=[{"entity":["product"]}]

    @Override
    public String toString() {
        HashMap<String, String> set = getStandardFilter();
        if (elements != null && elements.size() > 0) {
            Set entrySet = elements.entrySet();
            Iterator iterator = entrySet.iterator();
            JSONArray jsonParams = new JSONArray();

            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                try {
                    JSONObject conditionObject = new JSONObject();
                    JSONArray conditionValues = new JSONArray();
                    conditionValues.put(elements.get(key));
                    conditionObject.put(key, conditionValues);
                } catch (JSONException e) {
                    Log.e("GridFilter", e.getLocalizedMessage());
                }
            }
            set.put("params", jsonParams.toString());
        }
        return "";
    }

    private HashMap<String, String> getStandardFilter() {
        HashMap<String, String> set = new HashMap<>();

        for (Predicate predicate : filters) {
            set.put(predicate.field, predicate.value);
        }

        return set;
    }
}