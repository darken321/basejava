package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListTextSection extends Section<List<String>> {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final ListTextSection EMPTY = new ListTextSection("");
    private List<String> list;

    public ListTextSection() {
    }

    public ListTextSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListTextSection(List<String> list) {
        Objects.requireNonNull(list, "Text must not be null");
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
    public List<String> getSections() {
        return list;
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
        return list.toString();
    }
}
