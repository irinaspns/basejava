package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LocalDate from;
    private final LocalDate till;
    private final String title;
    private final String description;

    public Position(LocalDate from, LocalDate till, String title, String description) {
        Objects.requireNonNull(from, "from must not be null");
        Objects.requireNonNull(till, "till must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.from = from;
        this.till = till;
        this.title = title;
        this.description = description;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTill() {
        return till;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return from.equals(position.from) &&
                till.equals(position.till) &&
                title.equals(position.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, till, title, description);
    }

    @Override
    public String toString() {
        return "Positions{" +
                "from=" + from +
                ", till=" + till +
                ", title='" + title + '\'' +
                ", description=" + description +
                '}';
    }
}
