package com.urise.webapp.model;

import java.util.ArrayList;

public class ListTextSection extends AbstractSection {
    private ArrayList<String> arraySection;

    public ArrayList<String> getArraySection() {
        return arraySection;
    }

    public void setArraySection(ArrayList<String> arraySection) {
        this.arraySection = arraySection;
    }

    @Override
    public void printAll() {
        for (String st : arraySection) {
            System.out.println(st);
        }
    }
}
