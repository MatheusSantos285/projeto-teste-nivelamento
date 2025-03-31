package com.empresa.filemanager.download;

import com.empresa.filemanager.exception.DownloadException;
import java.io.IOException;
import java.net.http.*;
import java.nio.file.*;

public interface FileDownloader {
    String[] downloadFiles(String[] urls, String outputDir) throws IOException;
}