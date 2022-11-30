package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage.set(index, r);
    }

    protected void saveResume(int index, Resume r) {
        storage.add(r);
    }

    protected void deleteResume(int index, String uuid) {
        storage.remove(get(uuid));
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected Resume getResume(int index, String uuid) {
        return storage.get(getIndex(uuid));
    }

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}