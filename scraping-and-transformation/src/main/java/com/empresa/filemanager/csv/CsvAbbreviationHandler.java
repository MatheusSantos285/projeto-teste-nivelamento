package com.empresa.filemanager.csv;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CsvAbbreviationHandler {
    private final Map<String, String> abbreviationsMap;

    public CsvAbbreviationHandler(Map<String, String> abbreviationsMap) {
        this.abbreviationsMap = abbreviationsMap;
    }

    public void processFile(String inputPath, String outputPath) throws IOException {
        List<String> processedLines = Files.lines(Paths.get(inputPath))
                .map(this::replaceAbbreviations)
                .collect(Collectors.toList());

        Files.write(Paths.get(outputPath), processedLines);
    }

    private String replaceAbbreviations(String line) {
        String processedLine = line;
        for (Map.Entry<String, String> entry : abbreviationsMap.entrySet()) {
            processedLine = processedLine.replace(
                    "\"" + entry.getKey() + "\"",
                    "\"" + entry.getValue() + "\""
            );
        }
        return processedLine;
    }
}