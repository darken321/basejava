package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    protected void updateResume(Integer index, Resume r) {
        storage.set(index, r);
    }

    protected void saveResume(Integer index, Resume r) {
        storage.add(r);
    }

    protected void deleteResume(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected List<Resume> getListCopy() {
        return new ArrayList<>(storage);
    }

    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    protected boolean isExist(Integer searchKey) {
        return (searchKey >= 0);
    }

    protected Integer getSearchKey(String uuid) {
        Resume[] ar = new Resume[storage.size()];
        storage.toArray(ar);
        for (int i = 0; i < storage.size(); i++) {
            if (ar[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}