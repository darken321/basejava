package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(int index, Resume r) {
        storage[index] = r;
    }

    protected void saveResume(int index, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }

    protected void deleteResume(int index, String uuid) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    protected Resume getResume(int index, String uuid) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);
}
