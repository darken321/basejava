package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String r) {
        super("Resume already exist", r);
    }
}
