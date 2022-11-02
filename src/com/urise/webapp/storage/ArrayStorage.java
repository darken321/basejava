package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size; //число резюме

    public void clear() {
        for (int i = 0; i < size; i++) { //стираю резюме
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (r.uuid != null) { //если на входе не null
            storage[size] = r; //сохраняю в последнюю свободную ячейку
            size++;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) { //если элемент совпал с uuid
                return storage[i];
            }
        }
        return null; //если не совпал с uuid
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) { //ищем резюме uuid
                storage[i]=storage[size-1];
                storage[size-1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size]; //создаем новый массив длиной с число резюме
        System.arraycopy(storage, 0, allResume, 0, size); //копируем в него часть массива с резюме
        return allResume;
    }

    public int size() {
        return size;
    }
}
