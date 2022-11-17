package com.urise.webapp.exception;

public class ExistStorageExceptions extends StorageException{
    public ExistStorageExceptions(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
