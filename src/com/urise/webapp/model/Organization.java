package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final String title;
    private String url;
    private final List<TimeBlock> timeBlocks;

    public Organization(String title, List<TimeBlock> timeBlocks) {
        assert title == null || timeBlocks == null: " Title en timeBlocks can not be null";
        this.title = title;
        this.timeBlocks = timeBlocks;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    public void addTimeBlock(TimeBlock timeBlock) {
        this.timeBlocks.add(timeBlock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) &&
                Objects.equals(url, that.url) &&
                timeBlocks.equals(that.timeBlocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, timeBlocks);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", timeBlocks=" + timeBlocks +
                '}';
    }
}
