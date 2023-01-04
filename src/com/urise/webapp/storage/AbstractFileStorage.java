package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

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
        // читает все файлы, используя doRead
        List<Resume> list = new ArrayList<>();
        for (File f : directory.listFiles()) {
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
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file);

    protected abstract void doDelete(File file);

    protected abstract Resume doRead(File file);

    @Override
    protected void deleteResume(File file) {
        // удаляет файл
        doDelete(file);
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
        for (File f : directory.listFiles()) {
            f.delete();
        }
    }

    @Override
    public int size() {
        return directory.listFiles().length;
    }
}