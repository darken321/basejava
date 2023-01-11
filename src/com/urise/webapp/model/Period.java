package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Period implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String startDate;
    private final String endDate;
    private final String title;
    private final String description;

    public Period(String startDate, String stopDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = stopDate;
        this.title = title;
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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
        return startDate.equals(period.startDate) && Objects.equals(endDate, period.endDate) && title.equals(period.title) && description.equals(period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "StartDate='" + startDate + '\'' +
                ", StopDate='" + endDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
