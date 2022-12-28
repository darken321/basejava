package com.urise.webapp.model;

public class TextSection extends AbstractSection {
    public String stringSection;

    public String getTextSection() {
        return stringSection;
    }

    public void setTextSection(String stringSection) {
        this.stringSection = stringSection;
    }

    @Override
    public void printAll() {
        System.out.println(stringSection);
    }
}
