package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveStorage(Resume r, int index) {
        if (-index - 1 < size) {
            System.arraycopy(storage, -index - 1, storage, -index, size + index + 1);
        }
        storage[-index - 1] = r;
        size++;
    }

    protected void deleteStorage(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index + 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
