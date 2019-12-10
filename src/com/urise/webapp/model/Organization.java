package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final String title;
    private final String url;
    private final List<Position> positions;

    public Organization(String title, String url, List<Position> positions) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(positions, "positions must not be null");
        this.title = title;
        this.url = url;
        this.positions = positions;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) &&
                url.equals(that.url) &&
                positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", positions=" + positions +
                '}';
    }
}
