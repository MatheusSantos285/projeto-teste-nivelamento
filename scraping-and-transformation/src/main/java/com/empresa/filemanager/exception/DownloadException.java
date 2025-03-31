package com.empresa.filemanager.exception;

import java.io.IOException;

public class DownloadException extends IOException {
    public DownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}