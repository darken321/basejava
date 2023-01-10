package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
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
            throw new StorageException("IO error ", directory.getName());
        }
        List<Resume> list = new ArrayList<>();
        for (File f : dir) {
            list.add(getResume(f));
        }
        return list;
    }

    @Override
    protected void updateResume(File file, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error ", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume r) {
        try {
            if (!file.createNewFile()) {
                throw new StorageException("Cant create file ", file.getName());
            }
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Can't delete file ", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String fileName) {
        return new File(directory, fileName);
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error ", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("IO error ", directory.getName());
        }
        for (File f : dir) {
            deleteResume(f);
        }
    }

    @Override
    public int size() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("IO error ", directory.getName());
        }
        return dir.length;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}
