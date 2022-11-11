package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Переполнение массива резюме");
        } else if (index >= 0) {
            System.out.println("Резюме " + r.getUuid() + " уже есть.");
        } else {
            if (-index - 1 < size) {
                System.arraycopy(storage, -index - 1, storage, -index, size + index + 1);
            }
            storage[-index - 1] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено.");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index + 1);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
