package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    final public void update(Resume r) {
        int searchKey = (int) getExistingSearchKey(r);
        updateResume(searchKey, r);
    }

    @Override
    final public void save(Resume r) {
        saveResume((int) getNotExistingSearchKey(r), r);
    }

    @Override
    final public void delete(String uuid) {
        deleteResume((int) getExistingSearchKey(new Resume(uuid)), uuid);
    }

    @Override
    final public Resume get(String uuid) {
        return getResume((int) getExistingSearchKey(new Resume(uuid)), uuid);
    }

    private Object getNotExistingSearchKey(Resume r) {
        int searchKey = (int) getSearchKey(r.getUuid());
        if (searchKey >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else return searchKey;
    }

    private Object getExistingSearchKey(Resume r) {
        int searchKey = (int) getSearchKey(r.getUuid());
        if (searchKey < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else return searchKey;
    }

    protected abstract void updateResume(int searchKey, Resume r);

    protected abstract void saveResume(int searchKey, Resume r);

    protected abstract void deleteResume(int searchKey, String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(int searchKey, String uuid);

    protected abstract boolean isExist();
}
