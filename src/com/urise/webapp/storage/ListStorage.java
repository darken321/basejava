package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    protected void updateResume(Object index, Resume r) {
        storage.set((int) index, r);
    }

    protected void saveResume(Object index, Resume r) {
        storage.add(r);
    }

    protected void deleteResume(Object index, String uuid) {
        storage.remove(get(uuid));
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected List<Resume> getListCopy(){
        return new ArrayList<>(storage);
    }

    protected Resume getResume(Object index, String uuid) {
        return storage.get((int) getSearchKey(new Resume(uuid)));
    }

    protected boolean isExist(Object searchKey) {
        return ((int)searchKey >= 0);
    }

    protected Object getSearchKey(Resume r) {
        Resume[] ar = new Resume[storage.size()];
        storage.toArray(ar);
        for (int i = 0; i < storage.size(); i++) {
            if (ar[i].getUuid() == r.getUuid()) {
                return i;
            }
        }
        return -1;
    }
}