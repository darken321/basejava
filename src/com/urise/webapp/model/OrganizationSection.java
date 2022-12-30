package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final ArrayList<Organization> organizations;

    public OrganizationSection(ArrayList<Organization> organizations) {
        this.organizations = organizations;
    }

    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public void printAll() {
        for (Organization cs : organizations) {
            System.out.println(cs.getName());
            System.out.println(cs.getWebsite());
            for (Period p : cs.getPeriod()) {
                System.out.println(p.getStartDate() + " - " + p.getStopDate());
                System.out.println(p.getTitle());
                System.out.println(p.getDescription());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }
}
