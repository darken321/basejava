package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapUuidStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new MapUuidStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Вася");
        Resume r2 = new Resume("uuid2", "Дима");
        Resume r3 = new Resume("uuid3", "Петя");
        Resume r4 = new Resume("uuid4","Маша");
        Resume r5 = new Resume("uuid5","Лена");
        Resume r6 = new Resume("uuid2","Женя");

        //ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);

        //ARRAY_STORAGE.save(r6);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getFullName()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        r2 = r6;
        System.out.println("\nUpdate r2:");

        ARRAY_STORAGE.update(r2);

        printAll();
        System.out.println("удаляю r3");
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}