package com.urise.webapp;

import com.urise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance; //= new TestSingleton();

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
        System.out.println("Сработал конструктор синглтона");
    }

    public enum Singleton{
        INSTANCE, NOT_INSTANCE
    }

    public static void main(String[] args) {
        System.out.println(getInstance().toString());
        System.out.println(Singleton.valueOf("INSTANCE"));
        Singleton instance1 = Singleton.valueOf("INSTANCE");
        System.out.println("Порядковый номер " + instance1.ordinal() + " " + instance1);
        Singleton instance2 = Singleton.valueOf("NOT_INSTANCE");
        System.out.println("Порядковый номер " + instance2.ordinal() + " " + instance2);

        System.out.println();
        for (Singleton s :Singleton.values()){
            System.out.println(s);
        }

        for (SectionType st:SectionType.values()) {
            System.out.println();
            System.out.println(st.ordinal());
            System.out.println(st);
            System.out.println(st.getTitle());
        }
    }
}