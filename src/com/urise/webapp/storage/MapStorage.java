package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    public void clear() {
    }

    @Override
    protected void updateResume(int Index, Resume r) {
    }

    @Override
    protected void saveResume(int index, Resume r) {
    }

    @Override
    protected void deleteResume(int index, String uuid) {
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
    protected Resume getResume(int index, String uuid) {
        return null;
    }

    protected int getIndex(String uuid) {
        return 0;
    }
}
