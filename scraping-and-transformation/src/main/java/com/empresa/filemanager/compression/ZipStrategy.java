package com.empresa.filemanager.compression;

import java.io.*;
import java.util.zip.*;

public class ZipStrategy implements CompressionStrategy {
    private static final int BUFFER_SIZE = 1024;

    @Override
    public void compress(String[] files, String outputPath) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputPath))) {
            byte[] buffer = new byte[BUFFER_SIZE];

            for (String filePath : files) {
                addFileToZip(zos, new File(filePath), buffer);
            }
        }
    }

    private void addFileToZip(ZipOutputStream zos, File file, byte[] buffer) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);

            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
    }
}