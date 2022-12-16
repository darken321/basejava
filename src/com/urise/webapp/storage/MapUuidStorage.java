package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(Object key, String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected List<Resume> getListCopy() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(Object key, String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(Resume r) {
        return r.getUuid();
    }
}
