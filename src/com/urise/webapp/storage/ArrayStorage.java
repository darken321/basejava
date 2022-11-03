package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int MAX_STORAGE = 10000;
    private Resume[] storage = new Resume[MAX_STORAGE];
    private int size; // число резюме

    public void clear() {
        fill(storage, 0, size-1, null);
        size = 0;
    }

    public void update(Resume r) {
        if (get(r.getUuid()) != null) { // такое резюме есть в storage
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == r.getUuid()) { // ищем индекс резюме в массиве
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("Резюме " + r.getUuid() + " не найдено.");
        }
    }

    public void save(Resume r) { // три проверки - что на входе не null, что резюме не найдено и что storage переполнено

        if (r.getUuid() == null) {
            System.out.println("Вы задали пустое uuid.");
        } else {
            if (size >= MAX_STORAGE) {
                System.out.println("Переполнение массива резюме");
            } else {
                if (get(r.getUuid()) != null) { //если такое резюме уже есть
                    System.out.println("Резюме " + r.getUuid() + " уже есть.");
                } else {
                    storage[size] = r; // сохраняю в последнюю свободную ячейку
                    size++;
                }
            }
        }
    }

    public Resume get(String uuid) { // если есть, возвращает резюме, если нет null
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) { // если элемент совпал с uuid
                return storage[i];
            }
        }
        return null; // если не совпал с uuid
    }

    public void delete(String uuid) {
        if (get(uuid) != null) { // проверяем, есть ли такое резюме
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) { // ищем резюме uuid
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else {
            System.out.println("Резюме " + uuid + " не найдено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size]; // создаем новый массив длиной с число резюме
        System.arraycopy(storage, 0, allResume, 0, size); // копируем в него часть массива с резюме
        return allResume;
    }

    public int size() {
        return size;
    }
}
