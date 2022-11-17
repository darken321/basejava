package com.urise.webapp.exception;

public class NotExistStorageExceptions extends StorageException {
    public NotExistStorageExceptions(String uuid) {
        super("Resume " + uuid + " not exist" , uuid);
    }
}