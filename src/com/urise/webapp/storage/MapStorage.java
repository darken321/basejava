package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    protected Map<String,Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void deleteResume(Object index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected List <Resume> getListCopy(){
        return new ArrayList<>(storage.values());
    }
    @Override
    protected Resume getResume(Object index, String uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    protected Object getSearchKey(String uuid) {
        return uuid;
    }
}
