package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class TimeBlock {

    private final LocalDate from;
    private final LocalDate till;
    private final String title;
    private String description;

    public TimeBlock(LocalDate from, LocalDate till, String title) {
        assert from == null || till == null || title == null : " From, till and title can not be null";
        this.from = from;
        this.till = till;
        this.title = title;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeBlock block = (TimeBlock) o;
        return from.equals(block.from) &&
                till.equals(block.till) &&
                title.equals(block.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, till, title);
    }

    @Override
    public String toString() {
        return "TimeBlock{" +
                "from=" + from +
                ", till=" + till +
                ", title='" + title + '\'' +
                ", description=" + description +
                '}';
    }
}
