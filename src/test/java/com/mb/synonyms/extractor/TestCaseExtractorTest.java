package com.mb.synonyms.extractor;


import com.mb.synonyms.dto.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCaseExtractorTest {

    @Test
    public void checkFileNameValidity_valid() {
        final String fileName = "file-name";
        final TestCaseExtractor underTest = new TestCaseExtractor(fileName);
        underTest.checkFileNameValidity(fileName);
    }

    @Test
    public void checkFileNameValidity_invalid() {
        final String fileName = "file-name";
        final TestCaseExtractor underTest = new TestCaseExtractor(fileName);
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.checkFileNameValidity(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.checkFileNameValidity(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.checkFileNameValidity("     "));
    }

    @Test
    public void parseNum_valid() {
        final String fileName = "file-name";
        final TestCaseExtractor underTest = new TestCaseExtractor(fileName);

        final String strNum = "  2";
        underTest.parseNum(strNum);
    }

    @Test
    public void parseNum_invalid() {
        final String fileName = "file-name";
        final TestCaseExtractor underTest = new TestCaseExtractor(fileName);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String strNum = "asdasd sadsd";
            underTest.parseNum(strNum);
        });
    }

    @Test
    public void extract() {
        final String fileName = "src/test/resources/example.in";
        final TestCaseExtractor underTest = new TestCaseExtractor(fileName);

        final List<TestCase> result = underTest.extract();
        Assertions.assertEquals(2, result.size(), "Expected different number of test cases");
    }
}
