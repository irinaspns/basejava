package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ChapterSection extends Section {

    private List<Chapter> chapters = new ArrayList<>();

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }
}
