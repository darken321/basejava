package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage {
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
        storage.remove(((Resume) key).getUuid());
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
        return (Resume) key;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
