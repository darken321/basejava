package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section<List<Organization>> {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations,"Organizations must not be null");
        this.organizations = organizations;
    }

    @Override
    public void printAll() {
        for (Organization cs : organizations) {
            System.out.println(cs.getName());
            System.out.println(cs.getWebsite());
            for (Organization.Period p : cs.getPeriods()) {
                System.out.println(p.getStartDate() + " - " + p.getEndDate());
                System.out.println(p.getTitle());
                System.out.println(p.getDescription());
            }
        }
    }

    @Override
    public List<Organization> getSections() {
        return organizations;
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
        return organizations.toString();
    }
}
