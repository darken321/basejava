package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

public class MainCollections {
    private static final Resume RESUME_1 = new Resume("uuid1", "Lena");
    private static final Resume RESUME_2 = new Resume("uuid3", "Misha");
    private static final Resume RESUME_3 = new Resume("uuid2", "Kolya");
    private static final Resume RESUME_4 = new Resume("uuid4", "Olya");
    private static final Resume RESUME_5 = new Resume("uuid2", "Olya");

    private final static Storage LIST_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        System.out.println("size " + LIST_STORAGE.size());
        printAll();

        LIST_STORAGE.save(RESUME_1);
        LIST_STORAGE.save(RESUME_2);
        LIST_STORAGE.save(RESUME_3);
        LIST_STORAGE.save(RESUME_4);

        System.out.println("\nsize " + LIST_STORAGE.size());
        printAll();

        System.out.println("update Resume 5 " + RESUME_4);
        LIST_STORAGE.update(RESUME_5);
        printAll();

        System.out.println("get 1 2 3");
        System.out.println(LIST_STORAGE.get("uuid1"));
        System.out.println(LIST_STORAGE.get("uuid2"));
        System.out.println(LIST_STORAGE.get("uuid3"));
        System.out.println("delete 1 2 3");
        LIST_STORAGE.delete("uuid1");
        LIST_STORAGE.delete("uuid2");
        LIST_STORAGE.delete("uuid3");
        printAll();

        LIST_STORAGE.clear();
        printAll();
    }

    static void printAll() {
        System.out.println();
        if (LIST_STORAGE.size() == 0) {
            System.out.println("empty");
        } else for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}