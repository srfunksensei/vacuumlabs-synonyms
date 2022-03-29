package com.mb.synonyms.extractor;

import com.mb.synonyms.dto.TestCase;
import com.mb.synonyms.range.Range;
import com.mb.synonyms.range.RangeInclusive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCaseExtractor {

    public static final Range<Integer> TEST_CASES_RANGE = new RangeInclusive<>(0, 100);
    public static final Range<Integer> SYNONYMS_DICTIONARY_RANGE = new RangeInclusive<>(0, 100);
    public static final Range<Integer> SYNONYMS_QUERIES_RANGE = new RangeInclusive<>(0, 100);

    private final String fileName;

    public TestCaseExtractor(final String fileName) {
        this.fileName = fileName;
    }

    public List<TestCase> extract() {
        checkFileNameValidity(this.fileName);

        final List<TestCase> testCases = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            final int numOfTestCases = parseNum(line);
            if (!TEST_CASES_RANGE.contains(numOfTestCases)) {
                throw new IllegalArgumentException("number is not in specific range!");
            }

            for (int i = 0; i < numOfTestCases; i++) {
                final List<String> synonyms = getStrings(reader, SYNONYMS_DICTIONARY_RANGE);
                final List<String> queries = getStrings(reader, SYNONYMS_QUERIES_RANGE);

                final TestCase testCase = new TestCase(synonyms, queries);
                testCases.add(testCase);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("file is not correctly formed and cannot be parsed");
        }
        return testCases;
    }

    protected List<String> getStrings(final BufferedReader reader, final Range<Integer> range) throws IOException {
        final List<String> strings = new ArrayList<>();

        String line = reader.readLine();
        int numOfLines = parseNum(line);
        if (!range.contains(numOfLines)) {
            throw new IllegalArgumentException("number is not in specific range!");
        }

        while (numOfLines-- > 0) {
            if ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        }

        return strings;
    }

    protected void checkFileNameValidity(final String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("empty/blank file name");
        }
    }

    protected int parseNum(final String line) {
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Cannot parse number");
        }
    }

}
