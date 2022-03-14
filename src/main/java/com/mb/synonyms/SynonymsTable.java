package com.mb.synonyms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SynonymsTable {

    private Map<String, Set<String>> SYNONYMS_TABLE = new HashMap<>();

    public Set<String> getSynonyms(final String word) {
        final Set<String> synonyms = SYNONYMS_TABLE.get(word.toLowerCase());
        if (synonyms == null) {
            return new TreeSet<>();
        }
        return new TreeSet<>(synonyms);
    }

    public void registerSynonym(final String word, final String... synonymsOfWord) {
        for (final String syn : synonymsOfWord) {
            final String lowerCasedWord = word.toLowerCase(),
                    lowerCasedSyn = syn.toLowerCase();

            putSynonymTable(lowerCasedWord, lowerCasedSyn); // synonym(String) = syn
            putSynonymTable(lowerCasedSyn, lowerCasedWord); // synonym(syn) = String
        }
    }

    private void putSynonymTable(final String word, final String synonymOfWord) {
        Set<String> syns = SYNONYMS_TABLE.get(word);
        if (syns == null) {
            syns = new TreeSet<>();
            SYNONYMS_TABLE.put(word, syns);
        }
        syns.add(synonymOfWord);
    }
}
