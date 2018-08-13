package com.spheremall.core.filters.elasticsearch.fulltext;

public class MatchPhrasePrefixFilter extends FullTextFilter {

    private static final String MAX_EXPANSIONS = "max_expansions";

    public MatchPhrasePrefixFilter(String field, String query) {
        super(field, query);
        this.name = "match_phrase_prefix";
    }

    public void setMaxExpansions(Integer maxExpansions) {
        queryParams.put(MAX_EXPANSIONS, maxExpansions);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
