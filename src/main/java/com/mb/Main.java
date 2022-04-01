package com.mb;

import com.mb.synonyms.SynonymChecker;
import com.mb.synonyms.dto.IOFiles;
import com.mb.synonyms.dto.TestCase;
import com.mb.synonyms.extractor.TestCaseExtractor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final IOFiles ioFiles = new IOFiles(args);

        final TestCaseExtractor testCaseExtractor = new TestCaseExtractor(ioFiles.getInputFile());
        final List<TestCase> testCases = testCaseExtractor.extract();

        final List<String> resultAfterCheck = new ArrayList<>();
        for (final TestCase testCase : testCases) {
            final SynonymChecker synonymChecker = new SynonymChecker(testCase.getSynonyms());
            resultAfterCheck.addAll(synonymChecker.check(testCase.getQueries()));
        }

        try {
            Files.write(Paths.get(ioFiles.getOutputFile()), resultAfterCheck, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
