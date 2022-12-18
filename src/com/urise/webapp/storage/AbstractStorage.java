package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public final void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public final void delete(String key) {
        Object searchKey = getExistingSearchKey(key);
        deleteResume(searchKey, key);
    }

    @Override
    public final Resume get(String key) {
        Object searchKey = getExistingSearchKey(key);
        return getResume(searchKey, key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageCopy = getListCopy();
        Comparator<Resume> byFullName = Comparator.comparing(obj -> obj.getFullName());
        Comparator<Resume> byFullNameUuid = byFullName.thenComparing(obj -> obj.getUuid());
        storageCopy.sort(byFullNameUuid);
        return storageCopy;
    }

    private Object getNotExistingSearchKey(String key) {
        Object searchKey = getSearchKey(key);
        if (isExist(searchKey)) {
            throw new ExistStorageException(key);
        }
        return searchKey;
    }

    private Object getExistingSearchKey(String key) {
        Object searchKey = getSearchKey(key);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(key);
        }
        return searchKey;
    }

    protected abstract List<Resume> getListCopy();

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract void deleteResume(Object searchKey, String key);

    protected abstract Object getSearchKey(String key);

    protected abstract Resume getResume(Object searchKey, String key);

    protected abstract boolean isExist(Object searchKey);
}

