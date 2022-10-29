import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
    }

    void save(Resume r) {
        int i = 0;
        while (storage[i] != null ) { //ищем пустой элемент
            i++;
        }
        storage[i] = r;
    }

    Resume get(String uuid) {
        int i = 0;
        while (storage[i]!=null&&!storage[i].uuid.equals(uuid)) {
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        int i = 0;
        while (storage[i] != null) {
            i++;
        };
        return i;
    }
}
