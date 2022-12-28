package com.urise.webapp.model;

public class CompanySection {
    private String name;
    private String website;
    private Period period;

    public CompanySection(String name, String link, Period period) {
        this.name = name;
        this.website = link;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public Period getPeriod() {
        return period;
    }
}
