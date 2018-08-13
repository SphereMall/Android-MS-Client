package com.spheremall.core.filters.elasticsearch.fulltext;

import com.spheremall.core.filters.elasticsearch.common.LeafQueryFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommonTermsFilter extends LeafQueryFilter {

    private static final String QUERY = "query";
    private static final String CUTOFF_FREQUENCY = "cutoff_frequency";
    private static final String LOW_FREQ_OPERATOR = "low_freq_operator";
    private static final String MINIMUM_SHOULD_MATCH = "minimum_should_match";
    private static final String LOW_FREQ = "low_freq";
    private static final String HIGH_FREQ = "high_freq";
    private static final String BODY = "body";

    private Map<String, Object> queryParams = new HashMap<>();

    public CommonTermsFilter(String query) {
        this.name = "common";
        queryParams.put(QUERY, query);
    }

    public void setCutoffFrequency(Double cutoffFrequency) {
        queryParams.put(CUTOFF_FREQUENCY, cutoffFrequency);
    }

    /**
     * For low frequency terms, set the low_freq_operator to "and" to make all terms required
     */
    public void setLowFreqOperator(String lowFreqOperator) {
        queryParams.put(LOW_FREQ_OPERATOR, lowFreqOperator);
    }

    /**
     * Indicates a fixed value regardless of the number of optional clauses.
     */
    public void setMinShouldMatch(int minShouldMatch) {
        queryParams.put(MINIMUM_SHOULD_MATCH, minShouldMatch);
    }

    /**
     * Indicates that this percent of the total number of optional clauses are necessary.
     * The number computed from the percentage is rounded down and used as the minimum.
     */
    public void setMinShouldMatch(String percentage) {
        queryParams.put(MINIMUM_SHOULD_MATCH, percentage);
    }

    public void setMinShouldMatch(int lowFreq, int highFreq) {
        try {
            JSONObject minShouldMatch = new JSONObject();
            minShouldMatch.put(HIGH_FREQ, highFreq);
            minShouldMatch.put(LOW_FREQ, lowFreq);
            queryParams.put(MINIMUM_SHOULD_MATCH, minShouldMatch);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject commonObject = new JSONObject();
        try {
            JSONObject propertiesObject = new JSONObject();

            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                propertiesObject.put(entry.getKey(), entry.getValue());
            }

            JSONObject bodyObject = new JSONObject();
            bodyObject.put(BODY, propertiesObject);
            commonObject.put(name, bodyObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return commonObject;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
