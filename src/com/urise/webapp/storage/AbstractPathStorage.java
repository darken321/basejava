package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Path.of(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
    }

    @Override
    protected List<Resume> getListCopy() {
        File[] dir = directory.toFile().listFiles();
        if (dir == null) {
            throw new StorageException("IO error ", null);
        }
        List<Resume> list = new ArrayList<>();
        for (File f : dir) {
            list.add(getResume(f.toPath()));
        }
        return list;
    }

    @Override
    protected void updateResume(Path path, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File write error ", directory.toFile().getName(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume r) {
        try {
            if (!path.toFile().createNewFile()) {
                throw new StorageException("Cant create file ", path.toFile().getName());
            }
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error ", directory.toFile().getName(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        if (!path.toFile().delete()) {
            throw new StorageException("Can't delete file ", directory.toFile().getName());
        }
    }

    @Override
    protected Path getSearchKey(String fileName) {
        return Path.of(directory + "\\" + fileName);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error ", directory.toFile().getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return path.toFile().exists();
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", directory.toFile().getName());
        }
    }

    @Override
    public int size() {
        File[] dir = directory.toFile().listFiles();
        if (dir == null) {
            throw new StorageException("IO error ", directory.toFile().getName());
        }
        return dir.length;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}