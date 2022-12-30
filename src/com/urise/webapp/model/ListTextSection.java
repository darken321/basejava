package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class ListTextSection extends AbstractSection {
    private ArrayList<String> list;

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
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
