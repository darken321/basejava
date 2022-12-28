package com.urise.webapp.model;

public class Period {
    private String StartDate;
    private String StopDate;
    private String title;
    private String description;

    public Period(String startDate, String stopDate, String title, String description) {
        StartDate = startDate;
        StopDate = stopDate;
        this.title = title;
        this.description = description;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getStopDate() {
        return StopDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
