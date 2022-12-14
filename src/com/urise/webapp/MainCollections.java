package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String NAME1 = "Lena";
    private static final String NAME2 = "Misha";
    private static final String NAME3 = "Kolya";
    private static final String NAME4 = "Ola";


    private static final Resume RESUME_1 = new Resume(UUID_3, NAME1);
    private static final Resume RESUME_2 = new Resume(UUID_5, NAME2);
    private static final Resume RESUME_3 = new Resume(UUID_1, NAME3);
    private static final Resume RESUME_4 = new Resume(UUID_2, NAME1);
    private static final Resume RESUME_5 = new Resume(UUID_5, NAME4);

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

        System.out.println("After sorting");
        for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
        printAll();

        System.out.println("update Resume 5 " + RESUME_5);
        LIST_STORAGE.update(RESUME_5);
        printAll();

        System.out.println("After sorting");
        for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }

        System.out.println("get 1 2 3");
        System.out.println(LIST_STORAGE.get(UUID_1));
        System.out.println(LIST_STORAGE.get(UUID_2));
        System.out.println(LIST_STORAGE.get(UUID_3));
        System.out.println("delete 1 2 3");
        LIST_STORAGE.delete(UUID_1);
        LIST_STORAGE.delete(UUID_2);
        LIST_STORAGE.delete(UUID_3);
        printAll();

        LIST_STORAGE.clear();
        printAll();
    }

    static void printAll() {
        System.out.println();
        if (LIST_STORAGE.size() == 0) {
            System.out.println("empty");
        } else for (Resume r : LIST_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
