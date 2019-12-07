package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter {

    private String titel;
    private String url;
    private List<TimeBlock> subChapters = new ArrayList<>();

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TimeBlock> getSubChapters() {
        return subChapters;
    }

    public void addSubChapters(TimeBlock subChapter) {
        this.subChapters.add(subChapter);
    }
}
