package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String website;
    private List<Period> period;

    public Organization() {
    }

    public Organization(String name, String website, List<Period> period) {
        Objects.requireNonNull(name,"Name must not be null");
        Objects.requireNonNull(period,"Period must not be null");
        this.name = name;
        this.website = Objects.requireNonNullElse(website, "");
        this.period = period;
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
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(website, that.website) && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, period);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", period=" + period +
                '}';
    }
}
