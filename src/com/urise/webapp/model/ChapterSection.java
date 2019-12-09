package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ChapterSection extends Section {

    private final List<Organization> organizations;

    public ChapterSection(List<Organization> organizations) {
        assert organizations == null : " Chapters can not be null";
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addChapter(Organization organization) {
        this.organizations.add(organization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterSection that = (ChapterSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return "ChapterSection{" +
                "organizations=" + organizations +
                '}';
    }
}
