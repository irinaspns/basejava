package com.urise.webapp.model;

import java.util.List;

public class TextSection {
    private SectionType type;
    private List<String> text;

    public TextSection(SectionType type) {
        this.type = type;
    }
}
