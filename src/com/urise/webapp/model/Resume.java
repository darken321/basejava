package com.urise.webapp.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private String name;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String name) {
        this(uuid);
        this.name = name;
    }

    public String getUuid() {
        return uuid;
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
        return (uuid + ": " + name);
    }

    @Override
    public int compareTo(@NotNull Resume o) {
        return uuid.compareTo(o.getUuid());
    }
}
