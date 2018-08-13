package com.spheremall.core.filters.elasticsearch.terms;

import com.spheremall.core.filters.elasticsearch.common.LeafQueryFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.spheremall.core.filters.elasticsearch.terms.RangeFilter.RangeType.GT;
import static com.spheremall.core.filters.elasticsearch.terms.RangeFilter.RangeType.GTE;
import static com.spheremall.core.filters.elasticsearch.terms.RangeFilter.RangeType.LT;
import static com.spheremall.core.filters.elasticsearch.terms.RangeFilter.RangeType.LTE;

public abstract class RangeFilter<Type> extends LeafQueryFilter {

    public final String attribute;
    private LinkedHashMap<String, Type> options;

    public RangeFilter(String attribute) {
        this.attribute = attribute;
        this.name = "range";
        this.options = new LinkedHashMap<>();
    }

    public void addOption(String optionName, Type value) {
        if (!optionName.equals(GT) && !optionName.equals(LT) && !optionName.equals(GTE) && !optionName.equals(LTE)) {
            throw new RuntimeException("OptionName in RangeFilter is not correct");
        }
        options.put(optionName, value);
    }

    @Override
    public JSONObject toJson() {
        JSONObject optionJson = new JSONObject();
        for (Map.Entry<String, Type> entry : options.entrySet()) {
            try {
                optionJson.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject attrObject = new JSONObject();
            attrObject.put(attribute, optionJson);
            JSONObject filterObject = new JSONObject();
            filterObject.put(name, attrObject);
            return filterObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    /**
     * gt: > greater than
     * lt: < less than
     * gte: >= greater than or equal to
     * lte: <= less than or equal to
     */
    public interface RangeType {
        String GT = "gt";
        String LT = "lt";
        String GTE = "gte";
        String LTE = "lte";
    }
}
