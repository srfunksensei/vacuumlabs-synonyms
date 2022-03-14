package com.mb.synonyms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SynonymsTableTest {

    @Test
    public void checkSynonyms() {
        final SynonymsTable underTest = new SynonymsTable();

        final String word = "big";
        final String wordSynonym = "large";
        underTest.registerSynonym(word, wordSynonym);

        Set<String> synonyms = underTest.getSynonyms(word);
        Assertions.assertTrue(synonyms.contains(wordSynonym));

        synonyms = underTest.getSynonyms(wordSynonym);
        Assertions.assertTrue(synonyms.contains(word));
    }

    @Test
    public void checkSynonyms_caseInsensitive() {
        final SynonymsTable underTest = new SynonymsTable();

        final String word = "BiG";
        final String wordSynonym = "laRge";
        underTest.registerSynonym(word, wordSynonym);

        Set<String> synonyms = underTest.getSynonyms(word);
        Assertions.assertTrue(synonyms.contains(wordSynonym.toLowerCase()));

        synonyms = underTest.getSynonyms(wordSynonym);
        Assertions.assertTrue(synonyms.contains(word.toLowerCase()));
    }

    @Test
    public void noSynonyms() {
        final SynonymsTable underTest = new SynonymsTable();

        final Set<String> synonyms = underTest.getSynonyms("word");
        Assertions.assertTrue(synonyms.isEmpty());
    }
}
