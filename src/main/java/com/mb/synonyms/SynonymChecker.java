package com.mb.synonyms;

import java.util.*;

public class SynonymChecker {

    protected static final String SYNONYMS = "synonyms";
    protected static final String DIFFERENT = "different";

    private final SynonymsTable synonymsTable;

    public SynonymChecker(final List<String> synonyms) {
        this.synonymsTable = initSynonymsTable(synonyms);
    }

    private static SynonymsTable initSynonymsTable(final List<String> synonyms) {
        final SynonymsTable synonymsTable = new SynonymsTable();
        if (synonyms != null) {
            for (final String synonym : synonyms) {
                final String[] split = synonym.trim().toLowerCase(Locale.ROOT).split(" ");
                if (split.length != 2) {
                    throw new IllegalArgumentException("Expected two synonym words");
                }
                synonymsTable.registerSynonym(split[0], split[1]);
            }
        }
        return synonymsTable;
    }

    public List<String> check(final List<String> queries) {
        final List<String> resultAfterCheck = new ArrayList<>();
        if (queries != null) {
            for (final String query : queries) {
                final String[] split = query.trim().toLowerCase(Locale.ROOT).split(" ");
                if (split.length != 2) {
                    throw new IllegalArgumentException("Expected two query words");
                }

                final String word = split[0], synonymToCheck = split[1];
                if (isSameWord(word, synonymToCheck) || isSynonymOfSynonyms(word, synonymToCheck)) {
                    resultAfterCheck.add(SYNONYMS);
                    continue;
                }

                resultAfterCheck.add(DIFFERENT);
            }
        }
        return resultAfterCheck;
    }

    private static boolean isSameWord(final String word, final String synonymToCheck) {
        return word.equalsIgnoreCase(synonymToCheck);
    }

    protected boolean isSynonymOfSynonyms(final String a, final String b) {
        final Queue<String> queueA = new LinkedList<>(Collections.singleton(a));
        final Queue<String> queueB = new LinkedList<>(Collections.singleton(b));

        final Set<String> visitedA = new HashSet<>(Collections.singleton(a));
        final Set<String> visitedB = new HashSet<>(Collections.singleton(b));

        // Both queues need to be empty to exit the while loop.
        while (!queueA.isEmpty() || !queueB.isEmpty()) {
            if (isSynonymOfSynonymsHelper(queueA, visitedA, visitedB)) {
                return true;
            }
            if (isSynonymOfSynonymsHelper(queueB, visitedB, visitedA)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSynonymOfSynonymsHelper(final Queue<String> queue,
                                                     final Set<String> visitedFromThisSide,
                                                     final Set<String> visitedFromThatSide) {
        if (!queue.isEmpty()) {
            final String next = queue.remove();

            final Set<String> adjacentStrings = synonymsTable.getSynonyms(next);

            for (final String adjacent : adjacentStrings) {

                // If the visited Strings, starting from the other direction,
                // contain the "adjacent" String of "next", then we can terminate the search
                if (visitedFromThatSide.contains(adjacent)) {
                    return true;
                } else if (visitedFromThisSide.add(adjacent)) {
                    queue.add(adjacent);
                }
            }
        }
        return false;
    }
}
