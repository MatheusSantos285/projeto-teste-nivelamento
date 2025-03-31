package com.empresa.filemanager.download;

import com.empresa.filemanager.exception.DownloadException;

import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class PdfDownloader implements FileDownloader {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @Override
    public String[] downloadFiles(String[] urls, String outputDir) throws IOException {
        List<String> downloadedFiles = new ArrayList<>();
        Path outputPath = Paths.get(outputDir);
        Files.createDirectories(outputPath);

        for (String url : urls) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            String fileName = url.substring(url.lastIndexOf('/') + 1);
            Path destination = outputPath.resolve(fileName);

            try {
                HttpResponse<Path> response = HTTP_CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofFile(destination)
                );

                if (response.statusCode() == 200) {
                    downloadedFiles.add(destination.toString());
                }
            } catch (Exception e) {
                throw new DownloadException("Falha ao baixar: " + url, e);
            }
        }
        return downloadedFiles.toArray(new String[0]);
    }
}