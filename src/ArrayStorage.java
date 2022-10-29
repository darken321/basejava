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
        //uuid = "345";
        int j = 0;
//        for (int i = 0; i < this.size(); i++) {
//
//        }
        System.out.println("get " + uuid + " = " + this.get(uuid)); //нашел нужную строку
        while (this.get(uuid)!=null & j<10 ) { //можно заменить на IF если искать один элемент while чтоб удалить все
          System.out.println("нашел " + ++j);
          //тут надо делать поиск элемента равного uuid с индексом и удаление его
        }
//        while (this.get(uuid)!=null & j<10) {  //тут я тестирую нашел ли строку uuid в storage
//            System.out.println("нашел " + ++j); //лимит в 10 ибо программа уходит к границе int
//        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r= new Resume[this.size()];
        if (this.size() >= 0) System.arraycopy(storage, 0, r, 0, this.size());
        //return new Resume[0];
        return r;
    }

    int size() {
        int i = 0;
        while (storage[i] != null) {
            i++;
        };
        return i;
    }
}
