package com.urise.webapp.model;

import java.util.List;

public class ChapterSection extends TextSection {

    private String titel;
    private String link;
    private List<SubChapter> subChapters;

    public ChapterSection(SectionType type) {
        super(type);
    }
}
