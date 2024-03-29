package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String startDate;
    private String endDate;
    private String title;
    private String description;

    public Period() {
    }

    public Period(String startDate, String stopDate, String title, String description) {
        Objects.requireNonNull(startDate, "Start date must not be null");
        Objects.requireNonNull(stopDate, "Stop date must not be null");
        Objects.requireNonNull(title, "Title  must not be null");

        this.startDate = startDate;
        this.endDate = stopDate;
        this.title = title;
        this.description = Objects.requireNonNullElse(description, "");
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
    public String getHtmlDescription() {
        if (description==null || description.trim().equals("")) {
            return "";
        } else return description;
    }
    public boolean isHtmlDescriptionEmpty() {
        return (description==null || description.trim().equals(""));
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
