package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    protected StreamStrategy streamStrategy;

    protected FileStorage(File directory, StreamStrategy streamStrategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamStrategy = streamStrategy;
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
        List<Resume> list = new ArrayList<>();
        for (File f : getDirList()) {
            list.add(getResume(f));
        }
        return list;
    }

    @Override
    protected void updateResume(File file, Resume r) {
        try {
            streamStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
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
            streamStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
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
            return streamStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
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
        for (File f : getDirList()) {
            deleteResume(f);
        }
    }

    @Override
    public int size() {
        return getDirList().length;
    }

    private File[] getDirList() {
        File[] dir = directory.listFiles();
        if (dir == null) {
            throw new StorageException("IO error ", directory.getName());
        }
        return dir;
    }

}
