/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size; //число резюме

    void clear() {
        for (int i = 0; i < size; i++) { //стираю резюме
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (r.uuid != null) { //если на входе не null
            storage[size] = r; //сохраняю в последнюю свободную ячейку
            size++;
        }
    }

    Resume get(String uuid) {
        int i = 0;
        while (i < size && !storage[i].uuid.equals(uuid)) { //ищем uuid
            i++;
        }
        if (i >= size){ //не нашел, нет резюме i(0) = size(0)
            return null;
        }
        else {
            return storage[i]; //нашел
        }
    }

    void delete(String uuid) {
        int i = 0;
        while (get(uuid) != null) { //пока элементы равные uuid есть в резюме, крутим цикл
            while (i< size && storage[i].uuid.equals(uuid)) { //ищем резюме uuid
                System.arraycopy(storage, i + 1, storage, i, size - i); //сдвигаю массив начиная с элемента i
                size--;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size]; //создаем новый массив длиной с число резюме
        System.arraycopy(storage, 0, allResume, 0, size); //копируем в него часть массива с резюме
        return allResume;
    }

    int size() {
        return size;
    }
}
