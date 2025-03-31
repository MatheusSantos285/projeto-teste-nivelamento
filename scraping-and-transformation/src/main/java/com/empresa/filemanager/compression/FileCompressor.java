package com.empresa.filemanager.compression;

import java.io.IOException;

public class FileCompressor {
    private final CompressionStrategy strategy;

    public FileCompressor(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compressFiles(String[] files, String outputPath) throws IOException {
        strategy.compress(files, outputPath);
    }
}