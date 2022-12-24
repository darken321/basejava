package com.urise.webapp.model;

public class ClassSection {
    private String name;
    private String link;
    private String timeStart;
    private String timeStop;
    private String header;
    private String description;

    public ClassSection(String name, String link, String timeStart, String timeStop, String header, String description) {
        this.name = name;
        this.link = link;
        this.timeStart = timeStart;
        this.timeStop = timeStop;
        this.header = header;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeStop() {
        return timeStop;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
}
