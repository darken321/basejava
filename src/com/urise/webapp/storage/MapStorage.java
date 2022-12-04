package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    public void clear() {
    }

    @Override
    protected void updateResume(Object index, Resume r) {
    }

    @Override
    protected void saveResume(Object index, Resume r) {
    }

    @Override
    protected void deleteResume(Object index, String uuid) {
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected Resume getResume(Object index, String uuid) {
        return null;
    }

    protected Object getSearchKey(String uuid) {
        return 0;
    }
}
