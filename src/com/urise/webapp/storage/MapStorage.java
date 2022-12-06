package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    protected TreeMap<String,Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.replace(r.getUuid(),r);
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

    @Override
    public Resume[] getAll() {
        ArrayList <Resume> ar = new ArrayList<>();
        for (Map.Entry<String,Resume> entry :storage.entrySet()){
            ar.add(entry.getValue());
        }
        return ar.toArray(new Resume[0]);
    }

    @Override
    protected Resume getResume(Object index, String uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Object searchKey) {
        if (searchKey == null) {
            return false;
        }
        return storage.containsKey((String) searchKey);
    }

    protected Object getSearchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        } else return null;
    }
}
