package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.set((int) index, r);
    }

    protected void saveResume(Object index, Resume r) {
        storage.add(r);
    }

    protected void deleteResume(Object index, String uuid) {
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

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> storageCopy = new ArrayList<>(storage);
        storageCopy.sort(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int nameCompare = o1.getFullName().compareTo(o2.getFullName());
                if (nameCompare == 0) {
                    return o1.getUuid().compareTo(o2.getUuid());
                } else return nameCompare;
            }
        });
        return storageCopy;
    }


    protected Resume getResume(Object index, String uuid) {
        return storage.get((int) getSearchKey(uuid));
    }

    protected boolean isExist(Object searchKey) {
        return ((int)searchKey >= 0);
    }

    protected Object getSearchKey(String uuid) {
        Resume[] ar = new Resume[storage.size()];
        storage.toArray(ar);
        for (int i = 0; i < storage.size(); i++) {
            if (ar[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}