package com.urise.webapp.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private EnumMap<ContactType, String> contacts;
    private EnumMap<SectionType, AbstractSection> sections;

    public void setContacts(EnumMap<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public EnumMap<ContactType, String> getContacts() {
        return contacts;
    }

    public void setSections(EnumMap<SectionType, AbstractSection> sections) {
        this.sections = sections;
    }

    public EnumMap<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.fullName = fullName;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return (uuid + ": " + fullName);
    }

    @Override
    public int compareTo(@NotNull Resume o) {
        int result = fullName.compareTo(o.fullName);
        if (result == 0) {
            return uuid.compareTo(o.uuid);
        }
        return result;
    }
}
