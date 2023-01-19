package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    protected StreamStrategy streamStrategy;

    protected PathStorage(String dir, StreamStrategy streamStrategy) {
        directory = Path.of(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamStrategy = streamStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
    }

    @Override
    protected List<Resume> getListCopy() {
        Stream<Path> dir;
        try {
            dir = Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("IO error", null,e);
        }
        List<Path> list = dir.toList();
        List<Resume> result = new ArrayList<>();
        for (Path p: list){
            result.add(getResume(p));
        }
        return result;
    }

    @Override
    protected void updateResume(Path path, Resume r) {
        try {
            streamStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
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
            streamStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
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
        return directory.resolve(fileName);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return streamStrategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
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
}