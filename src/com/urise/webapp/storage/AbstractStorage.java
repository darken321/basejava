package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        Object searchKey = getExistingSearchKey(r);
        updateResume(searchKey, r);
    }

    @Override
    public final void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r);
        saveResume(searchKey, r);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(new Resume(uuid));
        deleteResume(searchKey, uuid);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(new Resume(uuid));
        return getResume(searchKey, uuid);
    }

    private Object getNotExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    protected boolean isExist(Object searchKey) {
        return ((int)searchKey >= 0);
    }

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract void deleteResume(Object searchKey, String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey, String uuid);
}
