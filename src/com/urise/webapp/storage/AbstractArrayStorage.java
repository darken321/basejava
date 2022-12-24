package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage <Integer>{
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(Integer index, Resume r) {
        storage[index] = r;
    }

    protected void saveResume(Integer index, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doSave(r, index);
            size++;
        }
    }

    protected void deleteResume(Integer index) {
        doDelete(index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Integer searchKey) {
        return (searchKey >= 0);
    }

    protected Resume getResume(Integer index) {
        return storage[index];
    }

    protected List<Resume> getListCopy() {
        return new ArrayList<>(List.of(Arrays.copyOf(storage, size)));
    }

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(Integer index);
}
