package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Link link;
    private final List<Position> positions;

    public Organization(String title, String url, List<Position> positions) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(positions, "positions must not be null");
        this.positions = positions;
        this.link = new Link(title, url);
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) &&
                positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", positions=" + positions +
                '}';
    }
}
