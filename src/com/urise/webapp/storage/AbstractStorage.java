package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

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
    public final void delete(String key) {
        Object searchKey = getExistingSearchKey(new Resume(key, key));
        deleteResume(searchKey, key);
    }

    @Override
    public final Resume get(String key) {
        Object searchKey = getExistingSearchKey(new Resume(key, key));
        return getResume(searchKey, key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageCopy = getListCopy();
        storageCopy.sort((o1, o2) -> {
            int nameCompare = o1.getFullName().compareTo(o2.getFullName());
            if (nameCompare == 0) {
                return o1.getUuid().compareTo(o2.getUuid());
            }
            return nameCompare;
        });
        return storageCopy;
    }

    private Object getNotExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.toString());
        }
        return searchKey;
    }

    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.toString());
        }
        return searchKey;
    }

    protected abstract List<Resume> getListCopy();

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract void deleteResume(Object searchKey, String key);

    protected abstract Object getSearchKey(Resume r);

    protected abstract Resume getResume(Object searchKey, String key);

    protected abstract boolean isExist(Object searchKey);

}

