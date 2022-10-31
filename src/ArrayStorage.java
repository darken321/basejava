/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int storageSize = this.size();
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (r.uuid!=null) {
            storage[this.size()] = r;
        }
    }

    Resume get(String uuid) {
        int i = 0;
        while (storage[i] != null && !storage[i].uuid.equals(uuid)) {
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;
        while (this.get(uuid) != null) { //проверяем, если такой элемент есть в массиве
            while (storage[i] != null && storage[i].uuid.equals(uuid)) { //сдвигаю по одному
                int storageSize = this.size();
                for (int k = i; k < storageSize; k++) {
                    storage[k] = storage[k + 1];
                }
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int storageSize = this.size();
        Resume[] r = new Resume[storageSize];
        if (this.size() >= 0) System.arraycopy(storage, 0, r, 0, this.size());
        return r;
    }

    int size() {
        int i = 0;
        while (storage[i] != null) {
            i++;
        }
        return i;
    }
}
