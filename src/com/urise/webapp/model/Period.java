package com.urise.webapp.model;

import java.util.Objects;

public class Period {
    private final String StartDate;
    private final String StopDate;
    private final String title;
    private final String description;

    public Period(String startDate, String stopDate, String title, String description) {
        this.StartDate = startDate;
        this.StopDate = stopDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return StartDate.equals(period.StartDate) && Objects.equals(StopDate, period.StopDate) && title.equals(period.title) && description.equals(period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StartDate, StopDate, title, description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "StartDate='" + StartDate + '\'' +
                ", StopDate='" + StopDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
