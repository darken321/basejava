package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class MainCollections {

    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");
    private static final Resume RESUME_4 = new Resume("uuid4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        for (Resume r: collection) {
            System.out.println(r);
        }

    }
}
