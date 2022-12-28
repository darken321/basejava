package com.urise.webapp.model;

import java.util.ArrayList;

public class OrganizationSection extends AbstractSection{
    private ArrayList<CompanySection> classSection;

    public ArrayList<CompanySection> getClassSection() {
        return classSection;
    }

    public void setClassSection(ArrayList<CompanySection> classSection) {
        this.classSection = classSection;
    }

    @Override
    public void printAll() {
        for (CompanySection cs: classSection){
            System.out.println(cs.getName());
            System.out.println(cs.getWebsite());
            System.out.println(cs.getPeriod().getStartDate() + " - " + cs.getPeriod().getStopDate());
            System.out.println(cs.getPeriod().getTitle());
            System.out.println(cs.getPeriod().getDescription());
        }
    }
}
