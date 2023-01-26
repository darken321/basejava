package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.StreamStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
        return getListFiles().map(this::getResume).collect(Collectors.toList());
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
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Can't create file ", path.getFileName().toString(), e);
        }
        updateResume(path, r);
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
        getListFiles().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) getListFiles().count();
    }

    private Stream<Path> getListFiles(){
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("IO error ", directory.toFile().getName());
        }
    }
}