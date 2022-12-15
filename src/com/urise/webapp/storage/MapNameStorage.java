package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapNameStorage extends AbstractStorage {
    protected Map<String,Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object key, Resume r) { //key это ключ то есть имя
        storage.put((String)key,r); // сохраняет резюме r по ключу key
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        storage.put(r.getFullName(),r);
    }

    @Override
    protected void deleteResume(Object index, String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected List <Resume> getListCopy(){
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(Object index, String searchKey) {
        return storage.get(searchKey);
    }

    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey); // проверка есть ли searchKey в виде стринга - имя в storage
    }

    protected Object getSearchKey(Resume r) { // принимает резюме, возвращает searchKey - имя
        return r.getFullName(); // переименовать searchKey на r
    }
    //на этом уровне searchKey это имя
}
