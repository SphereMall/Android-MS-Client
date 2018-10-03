package com.spheremall.core.filters.elasticsearch.fulltext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;

public class MultiMatchFilter extends FullTextFilter {

    private static final String TYPE = "type";
    private static final String FIELDS = "fields";
    private static final String OPERATOR = "operator";
    private static final String TIE_BREAKER = "tie_breaker";
    private static final String ANALYZER = "analyzer";

    public MultiMatchFilter(String field, String query) {
        super(field, query);
        this.name = "multi_match";
    }

    /**
     * The way the multi_match query is executed internally depends on the type parameter, which can be set to:
     * best_fields, most_fields, cross_fields, phrase, phrase_prefix
     *
     * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/6.3/query-dsl-multi-match-query.html">Multi Match Query</a>
     */
    public void setType(String type) {
        queryParams.put(TYPE, type);
    }

    public void setFields(String... fields) {
        JSONArray array = new JSONArray(Arrays.asList(fields));
        queryParams.put(FIELDS, array);
    }

    public void setTieBreaker(Double tieBreaker) {
        queryParams.put(TIE_BREAKER, tieBreaker);
    }

    public void setOperator(String operator) {
        queryParams.put(OPERATOR, operator);
    }

    public void setAnalyzer(String analyzer) {
        queryParams.put(ANALYZER, analyzer);
    }

    @Override
    public JSONObject toJson() {
        JSONObject matchObject = new JSONObject();
        try {
            JSONObject propertiesObject = new JSONObject();

            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                propertiesObject.put(entry.getKey(), entry.getValue());
            }

            matchObject.put(name, propertiesObject);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        return matchObject;
    }
}
