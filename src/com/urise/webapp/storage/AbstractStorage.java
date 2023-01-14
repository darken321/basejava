package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage <SK> implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public final void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getExistingSearchKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public final void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public final void delete(String key) {
        LOG.info("Delete " + key);
        SK searchKey = getExistingSearchKey(key);
        deleteResume(searchKey);
    }

    @Override
    public final Resume get(String key) {
        LOG.info("Get " + key);
        SK searchKey = getExistingSearchKey(key);
        return getResume(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageCopy = getListCopy();
        storageCopy.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return storageCopy;
    }

    private SK getNotExistingSearchKey(String key) {
        SK searchKey = getSearchKey(key);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + key + " already exist");
            throw new ExistStorageException(key);
        }
        return searchKey;
    }

    private SK getExistingSearchKey(String key) {
        SK searchKey = getSearchKey(key);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + key + " already exist");
            throw new NotExistStorageException(key);
        }
        return searchKey;
    }

    protected abstract List<Resume> getListCopy();

    protected abstract void updateResume(SK searchKey, Resume r);

    protected abstract void saveResume(SK searchKey, Resume r);

    protected abstract void deleteResume(SK searchKey);

    protected abstract SK getSearchKey(String key);

    protected abstract Resume getResume(SK searchKey);

    protected abstract boolean isExist(SK searchKey);
}