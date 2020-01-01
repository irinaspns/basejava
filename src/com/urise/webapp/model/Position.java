package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate from;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate till;
    private String title;
    private String description;

    public Position() {
    }

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
