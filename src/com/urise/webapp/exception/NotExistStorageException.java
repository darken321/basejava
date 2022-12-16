package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String r) {
        super("Resume not exist", r);
    }
}