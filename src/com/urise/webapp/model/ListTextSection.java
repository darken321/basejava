package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListTextSection extends AbstractSection {
    private final List<String> list;

    public ListTextSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public void printAll() {
        for (String st : list) {
            System.out.println(st);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListTextSection that = (ListTextSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "ListTextSection{" +
                "list=" + list +
                '}';
    }
}
