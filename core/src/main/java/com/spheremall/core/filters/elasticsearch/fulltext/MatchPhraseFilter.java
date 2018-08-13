package com.spheremall.core.filters.elasticsearch.fulltext;

public class MatchPhraseFilter extends FullTextFilter {

    private static final String ANALYZER = "analyzer";

    public MatchPhraseFilter(String field, String query) {
        super(field, query);
        this.name = "match_phrase";
    }

    public void setAnalyzer(String analyzer) {
        queryParams.put(ANALYZER, analyzer);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
