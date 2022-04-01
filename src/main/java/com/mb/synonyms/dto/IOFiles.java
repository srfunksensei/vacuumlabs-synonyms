package com.mb.synonyms.dto;

import java.util.Optional;

public class IOFiles {

    private static final String EXAMPLE_FILE_IN = "src/test/resources/example.in";
    private static final String EXAMPLE_FILE_OUT = "src/test/resources/test-output.out";

    private final String inputFile;
    private final String outputFile;

    public IOFiles(final String[] args) {
        this.inputFile = extractFileName(args, "inputFile").orElse(EXAMPLE_FILE_IN);
        this.outputFile = extractFileName(args, "outputFile").orElse(EXAMPLE_FILE_OUT);
    }

    private Optional<String> extractFileName(final String[] args, final String paramName) {
        if (args != null) {
            for (final String s : args) {
                final String[] params = s.split("=");
                if (params[0].equalsIgnoreCase(paramName)) {
                    return Optional.of(params[1]);
                }
            }
        }
        return Optional.empty();
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
