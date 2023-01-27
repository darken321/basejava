package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public void printAll() {
        for (Organization cs : organizations) {
            System.out.println(cs.getName());
            System.out.println(cs.getWebsite());
            for (Period p : cs.getPeriod()) {
                System.out.println(p.getStartDate() + " - " + p.getEndDate());
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
