package com.mb.dto;

import java.util.List;

public class TestCase {

    private final List<String> synonyms;
    private final List<String> queries;

    public TestCase(final List<String> synonyms, final List<String> queries) {
        this.synonyms = synonyms;
        this.queries = queries;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getQueries() {
        return queries;
    }
}
