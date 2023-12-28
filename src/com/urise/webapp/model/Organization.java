package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", Period.EMPTY);
    private String name;
    private String website;
    private List<Period> periods;

    public Organization() {
    }

    public Organization(String name, String website, Period... positions) {
        this(name, website, Arrays.asList(positions));
    }

    public Organization(String name, String website, List<Period> period) {
        Objects.requireNonNull(name,"Name must not be null");
        Objects.requireNonNull(period,"Period must not be null");
        this.name = name;
        this.website = Objects.requireNonNullElse(website, "");
        this.periods = period;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }
    public String getHtmlWebsite() {
        if (website==null || website.trim().equals("")) {
            return "";
        } else return website;
    }
    public boolean isHtmlWebsiteEmpty() {
        return (website==null || website.trim().equals(""));
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(website, that.website) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
               "name='" + name + '\'' +
               ", website='" + website + '\'' +
               ", period=" + periods +
               '}';
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public static final Period EMPTY = new Period("","","","");
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
            return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
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
}
