package com.urise.webapp.model;

public enum SectionType {
    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    SectionType() {
    }

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
