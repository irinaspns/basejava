package com.urise.webapp.model;

import java.util.List;

public class ChapterSection extends Section {

    private List<Chapter> chapters;

    public ChapterSection(SectionType type) {
        super(type);
    }
}
