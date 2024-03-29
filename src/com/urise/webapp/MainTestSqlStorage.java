package com.urise.webapp;

import com.urise.webapp.model.ListTextSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import com.urise.webapp.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSqlStorage {
//    static final Storage SQL_STORAGE = new SqlStorage("jdbc:postgresql://localhost:5432/postgres",
//            "postgres",
//            "admin");
//    static final Storage SQL_STORAGE = new SqlStorage(Config.get().getConnectionFactory());
    static final Storage SQL_STORAGE = Config.get().getStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Григорий");
        Resume r2 = new Resume("uuid2", "Дима");
        Resume r3 = new Resume("uuid3", "Петя");
        Resume r4 = new Resume("uuid1","Маша");
        r1.setSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        r1.setSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> stringsArray1 = new ArrayList<>();
        stringsArray1.add("Организация команды и успешная реализация Java ");
        stringsArray1.add("С 2013 года: разработка проектов ");
        stringsArray1.add("Реализация протоколов по приему платежей всех основных платежных системы");
        r1.setSection(SectionType.ACHIEVEMENT, new ListTextSection(stringsArray1));

//        Resume r5 = new Resume("uuid5","Лена");
//        Resume r6 = new Resume("uuid2","Женя");
//        r1.setContacts(ContactType.PHONE, "123");
//        r1.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
//
//        r2.setContacts(ContactType.PHONE, "456");
//        r2.setContacts(ContactType.SKYPE, "skype 2");
//        r3.setContacts(ContactType.PHONE, "789");
//        r3.setContacts(ContactType.SKYPE, "skype 33:");


        SQL_STORAGE.clear();
        SQL_STORAGE.save(r2);
        SQL_STORAGE.save(r1);
//        SQL_STORAGE.save(r4);
        SQL_STORAGE.save(r3);
//        SQL_STORAGE.save(r5);
//        SQL_STORAGE.save(r2);

//        SQL_STORAGE.save(r6);


//        System.out.println("Get r1: " + SQL_STORAGE.get(r1.getFullName()));
//        System.out.println("Size: " + SQL_STORAGE.size());
//
//        System.out.println("Get dummy: " + SQL_STORAGE.get("dummy"));
//        r2 = r6;
//        System.out.println("\nUpdate r2:");
//
//        SQL_STORAGE.update(r2); //R2 был Дима должен стать Женя
//
//        printAll();
//        System.out.println("удаляю r3");
//        SQL_STORAGE.delete(r3.getUuid());
//        printAll();
//        SQL_STORAGE.clear();
//        System.out.println(SQL_STORAGE.get("uuid1"));
//        System.out.println(SQL_STORAGE.get("uuid2"));
//        System.out.println(SQL_STORAGE.get("uuid3"));
//        r1=r4;
//        r4.setContacts(ContactType.MAIL, "NO mail");
        System.out.println();
//        SQL_STORAGE.update(r1);

        printAll();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SQL_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}