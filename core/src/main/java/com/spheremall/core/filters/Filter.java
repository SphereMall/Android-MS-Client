package com.spheremall.core.filters;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Filter {

    protected List<Predicate> filters = new ArrayList<>();

    public Filter() {
    }

    public Filter(List<Predicate> filters) {
        if (filters.size() > 0) {
            setFilters(filters);
        }
    }

    public List<Predicate> getFilters() {
        return filters;
    }

    public Filter addFilter(String field, String operator, String value) {

        if (field == null || field.isEmpty())
            return this;

        filters.add(new Predicate(field, operator, value));
        return this;
    }

    public Filter setFilters(List<Predicate> filters) {
        for (Predicate predicate : filters) {
            if (predicate.field.equals("fullSearch")) {
                addFilter(predicate.field, null, predicate.value);
            }

            if (!isFilterExist(predicate)) {
                addFilter(predicate.field, predicate.operator, predicate.value);
            }
        }
        return this;
    }

    private boolean isFilterExist(Predicate predicate) {
        for (Predicate filter : filters) {
            if (filter.field.equals("fullSearch") && predicate.field.equals("fullSearch")) {
                return true;
            }

            if (filter.field.equals(predicate.field) && predicate.operator != null && filter.operator.equals(predicate.operator)) {
                return true;
            }
        }
        return false;
    }

    public Filter removeFilter(String field, String operator) {
        Iterator iterator = filters.iterator();
        while (iterator.hasNext()) {
            Predicate predicate = (Predicate) iterator.next();
            if (predicate.field.equals(field) && predicate.operator.equals(operator)) {
                iterator.remove();
            }
        }
        return this;
    }

    @Override
    public String toString() {

        JSONArray set = new JSONArray();

        for (Predicate predicate : filters) {
            set.put(compound(predicate));
        }
        try {
            if (set.length() == 1 && !set.getJSONObject(0).has("fullSearch")) {
                return set.get(0).toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return set.toString();
    }

    private JSONObject compound(Predicate predicate) {
        JSONObject jsonPredicate = new JSONObject();

        try {
            JSONObject condition = new JSONObject();
            if (predicate.operator != null) {
                condition.put(predicate.operator, predicate.value);
                jsonPredicate.put(predicate.field, condition);
            } else {
                jsonPredicate.put(predicate.field, predicate.value);
            }

        } catch (JSONException e) {
            Log.i(getClass().getSimpleName(), e.getLocalizedMessage());
        }
        return jsonPredicate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        return filters != null ? filters.equals(filter.filters) : filter.filters == null;
    }

    @Override
    public int hashCode() {
        return filters != null ? filters.hashCode() : 0;
    }
}
