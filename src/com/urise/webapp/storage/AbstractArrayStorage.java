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

    protected void updateResume(Object index, Resume r) {
        storage[(int)index] = r;
    }

    protected void saveResume(Object index, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r,(int)index);
            size++;
        }
    }

    protected void deleteResume(Object index, String uuid) {
        deleteResume((int)index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Object searchKey) {
        return ((int)searchKey >= 0);
    }

    protected Resume getResume(Object index, String uuid) {
        return storage[(int)index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
