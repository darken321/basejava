package com.urise.webapp.model;

import java.util.ArrayList;

public class Section {
    private String stringSection;
    private ArrayList<String> arraySection;
    private ArrayList<ClassSection> classSection;

    public String getStringSection() {
        return stringSection;
    }

    public void setStringSection(String stringSection) {
        this.stringSection = stringSection;
    }

    public ArrayList<String> getArraySection() {
        return arraySection;
    }

    public void setArraySection(ArrayList<String> arraySection) {
        this.arraySection = arraySection;
    }

    public ArrayList<ClassSection> getClassSection() {
        return classSection;
    }

    public void setClassSection(ArrayList<ClassSection> classSection) {
        this.classSection = classSection;
    }
}
