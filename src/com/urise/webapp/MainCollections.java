package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapNameStorage;
import com.urise.webapp.storage.Storage;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String NAME_1 = "Lena";
    private static final String NAME_2 = "Misha";
    private static final String NAME_3 = "Kolya";
    private static final String NAME_4 = "Ola";


    private static final Resume RESUME_1 = new Resume(UUID_3, NAME_1);
    private static final Resume RESUME_2 = new Resume(UUID_5, NAME_2);
    private static final Resume RESUME_3 = new Resume(UUID_1, NAME_3);
    private static final Resume RESUME_4 = new Resume(UUID_2, NAME_4);
    private static final Resume RESUME_5 = new Resume(UUID_4, NAME_4);

    private final static Storage LIST_STORAGE = new MapNameStorage();

    public static void main(String[] args) {

        System.out.println("size " + LIST_STORAGE.size());
        printAll();

        LIST_STORAGE.save(RESUME_1);
        LIST_STORAGE.save(RESUME_2);
        LIST_STORAGE.save(RESUME_3);
        LIST_STORAGE.save(RESUME_4);

        System.out.println("\nsize " + LIST_STORAGE.size());
        printAll();

        System.out.println("update Resume 5 " + RESUME_5);
        LIST_STORAGE.update(RESUME_5);
        printAll();

        System.out.println("get 1 2 3");
        System.out.println(LIST_STORAGE.get(NAME_1));
        System.out.println(LIST_STORAGE.get(NAME_2));
        System.out.println(LIST_STORAGE.get(NAME_3));
        System.out.println("delete 1 2 3");
        LIST_STORAGE.delete(NAME_1);
        LIST_STORAGE.delete(NAME_2);
        LIST_STORAGE.delete(NAME_3);
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