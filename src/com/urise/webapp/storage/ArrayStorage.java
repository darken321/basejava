package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size; // число резюме

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getResumeIndex(r.getUuid());
        if (index == -1) { // такого резюме нет в storage
            System.out.println("Резюме " + r.getUuid() + " не найдено.");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) { // две проверки - что резюме не найдено и что storage переполнено
        if (size >= STORAGE_LIMIT) {
            System.out.println("Переполнение массива резюме");
        } else {
            if (getResumeIndex(r.getUuid()) == -1) { // если такого резюме нет
                storage[size] = r; // сохраняю в последнюю свободную ячейку
                size++;
            } else {
                System.out.println("Резюме " + r.getUuid() + " уже есть.");
            }
        }
    }


    public Resume get(String uuid) { // если есть, возвращает резюме, если нет null
        if (getResumeIndex(uuid) == -1) {
            return null; // если не нашел uuid
        } else {
            return storage[getResumeIndex(uuid)];
        }
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index == -1) { // такого резюме нет в storage
            System.out.println("Резюме " + uuid + " не найдено.");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
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

    private int getResumeIndex(String uuid) { // возвращает номер резюме в storage или -1 если не нашел
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) { // если элемент совпал с uuid
                return i;
            }
        }
        return -1;
    }
}
