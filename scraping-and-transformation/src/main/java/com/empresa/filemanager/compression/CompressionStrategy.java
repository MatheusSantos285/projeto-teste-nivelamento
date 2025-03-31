package com.empresa.filemanager.compression;

import java.io.IOException;

public interface CompressionStrategy {
    void compress(String[] files, String outputPath) throws IOException;
}