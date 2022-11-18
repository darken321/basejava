package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("test UUID");
        System.out.println("\nr - " + r);

        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println("Can access before - " + field.canAccess(r));
        System.out.println("SetAccessible return - " + field.trySetAccessible());
        System.out.println("Can access after try - " + field.canAccess(r));
        System.out.println("\nfield - " + field);
        System.out.println("field.getName - " + field.getName());
        System.out.println("field.get(r) - " + field.get(r));
        field.set(r, "New uuid");
        System.out.println("After change - " + r);

        Method[] methods = r.getClass().getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println("\nString Через invoke\n");
        Method method = r.getClass().getMethod("toString");
        System.out.println(method);
        System.out.println(method.invoke(r));
    }
}
