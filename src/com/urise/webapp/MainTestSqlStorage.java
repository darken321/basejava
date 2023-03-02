package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSqlStorage {
//    static final Storage SQL_STORAGE = new SqlStorage("jdbc:postgresql://localhost:5432/postgres",
//            "postgres",
//            "admin");
//    static final Storage SQL_STORAGE = new SqlStorage(Config.get().getConnectionFactory());
    static final Storage SQL_STORAGE = new SqlStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Вася");
        Resume r2 = new Resume("uuid2", "Дима");
        Resume r3 = new Resume("uuid3", "Петя");
        Resume r4 = new Resume("uuid4","Маша");
        Resume r5 = new Resume("uuid5","Лена");
        Resume r6 = new Resume("uuid2","Женя");

        SQL_STORAGE.clear();
        SQL_STORAGE.save(r2);
        SQL_STORAGE.save(r1);
        SQL_STORAGE.save(r4);
        SQL_STORAGE.save(r3);
        SQL_STORAGE.save(r5);
//        SQL_STORAGE.save(r2);

//        SQL_STORAGE.save(r6);

//        System.out.println("Get r1: " + SQL_STORAGE.get(r1.getFullName()));
//        System.out.println("Size: " + SQL_STORAGE.size());
//
//        System.out.println("Get dummy: " + SQL_STORAGE.get("dummy"));
        r2 = r6;
//        System.out.println("\nUpdate r2:");
//
        SQL_STORAGE.update(r2); //R2 был Дима должен стать Женя
//
//        printAll();
//        System.out.println("удаляю r3");
//        SQL_STORAGE.delete(r3.getUuid());
//        printAll();
//        SQL_STORAGE.clear();
        printAll();
        System.out.println("Size: " + SQL_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SQL_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}