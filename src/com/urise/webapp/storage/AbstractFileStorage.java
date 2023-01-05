package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getListCopy() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("Directory empty", directory.getName());
        }
        List<Resume> list = new ArrayList<>();
        for (File f : dir) {
            list.add(doRead(f));
        }
        return list;
    }

    @Override
    protected void updateResume(File file, Resume r) {
        doWrite(r, file);
    }

    @Override
    protected void saveResume(File file, Resume r) {
        try {
            if (!file.createNewFile()) {
                throw new StorageException("Cant create file", file.getName());
            }
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws StorageException;

    protected abstract Resume doRead(File file) throws StorageException;

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Can't delete file", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String fileName) {
        return new File(directory, fileName);
    }

    @Override
    protected Resume getResume(File file) {
        return doRead(file);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("Directory empty", directory.getName());
        }
        for (File f : dir) {
            deleteResume(f);
        }
    }

    @Override
    public int size() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("Directory empty", directory.getName());
        }
        return dir.length;
    }
}