package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, int index) {
        System.arraycopy(storage, -index - 1, storage, -index, size + index + 1);
        storage[-index - 1] = r;
    }

    @Override
    protected void doDelete(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - index + 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "test");
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
    }
}
