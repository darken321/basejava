package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("test UUID");
        //Resume r = new Resume(); конструктор без параметров генерирует рандом uuid
        System.out.println("\nr= " + r);

        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println("can access before " + field.canAccess(r));
        System.out.println("can access try " + field.trySetAccessible());
        //field.setAccessible(true); //вариант вместо trySetAccessible
        System.out.println("can access after try " + field.canAccess(r));
        System.out.println("field.getName(r) " + field.getName());
        System.out.println("field.get(r) " + field.get(r));
        field.set(r, "New uuid");
        System.out.println(r);

        Method[] methods = r.getClass().getMethods(); // вывожу все имена методов
        for (Method m : methods) {
            //System.out.println(m); //полное имя
            System.out.println(m.getName());
        }
        //HW4 В MainReflection вызовите у Resume, через отражение, метод toString. Выведите результат на консоль
        System.out.println("\nЧерез invoke\n");
        Method method = r.getClass().getMethod("toString");
        System.out.println(method);
        System.out.println(method.invoke(r));
        //r.toString();

    }
}
