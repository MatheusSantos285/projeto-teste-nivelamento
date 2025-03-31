package com.empresa.filemanager.csv;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class CsvCleaner {
    public void cleanCsvFile(String inputPath, String outputPath) throws IOException {
        List<String> lines = Files.lines(Paths.get(inputPath))
                .filter(this::isValidLine)
                .collect(Collectors.toList());

        Files.write(Paths.get(outputPath), lines);
    }

    private boolean isValidLine(String line) {
        return !line.trim().isEmpty() && !line.trim().equals("\"\"");
    }
}