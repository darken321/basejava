/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int storageSize = this.size(); //сохраняю фиксирую число резюме
        for (int i = 0; i < storageSize; i++) { //стираю резюме
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (r.uuid != null) { //если на входе не null
            storage[this.size()] = r; //сохраняю в последнюю свободную ячейку
        }
    }

    Resume get(String uuid) {
        int i = 0;
        while (storage[i] != null && !storage[i].uuid.equals(uuid)) { //ищем uuid пока ячейки не null
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;
        while (this.get(uuid) != null) { //пока элементы равные uuid есть в резюме, крутим цикл
            while (storage[i] != null && storage[i].uuid.equals(uuid)) { //ищем резюме uuid
                int storageSize = this.size(); //сохранил число резюме
                //сдвигаю массив начиная с элемента i
                System.arraycopy(storage, i + 1, storage, i, storageSize - i);
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r = new Resume[this.size()]; //создаем новый массив длиной с число резюме
        //копируем в него часть массива с резюме
        System.arraycopy(storage, 0, r, 0, this.size());
        return r;
    }

    int size() {
        int i = 0;
        while (storage[i] != null) { //пробегаем по резюме пока не null
            i++;
        }
        return i;
    }
}
