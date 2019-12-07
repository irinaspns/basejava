package com.urise.webapp.model;

public class TextSection extends Section {

    private String text;

    public TextSection(SectionType type) {
        super(type);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
