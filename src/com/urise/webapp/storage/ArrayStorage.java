package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void saveStorage(Resume r, int index) {
        storage[size] = r;
        size++;
    }

    protected void deleteStorage(int index) {
        storage[index] = storage[size - 1];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
