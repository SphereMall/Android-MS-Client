package com.spheremall.core.filters.elasticsearch.fulltext;

public class MatchFilter extends FullTextFilter {

    private static final String OPERATOR = "operator";
    private static final String CUTOFF_FREQUENCY = "cutoff_frequency";
    private static final String AUTO_GENERATE_SYNONYMS_PHRASE_QUERY = "auto_generate_synonyms_phrase_query";
    private static final String ZERO_TERMS_QUERY = "zero_terms_query";

    public MatchFilter(String field, String query) {
        super(field, query);
        this.name = "match";
    }

    public void setOperator(String operator) {
        queryParams.put(OPERATOR, operator);
    }

    public void setZeroTermsQuery(String zeroTermsQuery) {
        queryParams.put(ZERO_TERMS_QUERY, zeroTermsQuery);
    }

    public void setCutoffFrequency(Double cutoffFrequency) {
        queryParams.put(CUTOFF_FREQUENCY, cutoffFrequency);
    }

    public void setAutoGenerateSynonymsPhraseQuery(boolean autoGenerateSynonymsPhraseQuery) {
        queryParams.put(AUTO_GENERATE_SYNONYMS_PHRASE_QUERY, autoGenerateSynonymsPhraseQuery);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
