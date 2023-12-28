package com.urise.webapp.model;

public enum ContactType {
    PHONE("Тел."),
    PHONEMOBILE("Мобильный"),
    PHONEHOME("Телеграм"),
    SKYPE("Skype"){
        @Override
        public String toHtml0(String value) {
            return "Скайп <a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта"){
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    ContactType() {
    }

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    /**
     * если значение null то возвращает ""
     * @param value
     * @return
     */
    public String toHtml(String value) {
        return (value==null) ? "" : toHtml0(value);
    }
}
