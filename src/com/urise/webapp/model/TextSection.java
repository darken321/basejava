package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    public String text;

    public String getTextSection() {
        return text;
    }

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public void printAll() {
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
