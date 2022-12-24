package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage <String>{
    protected Map<String, Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(String key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(String key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(String key) {
        storage.remove(key);
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
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
