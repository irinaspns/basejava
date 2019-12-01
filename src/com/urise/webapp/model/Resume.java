package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacten = new HashMap<>();
    private TextSection position = new TextSection(SectionType.OBJECTIVE);
    private TextSection personal  = new TextSection(SectionType.PERSONAL);
    private TextSection achievements = new TextSection(SectionType.ACHIEVEMENT);
    private TextSection qualification = new TextSection(SectionType.QUALIFICATIONS);
    private ChapterSection experience = new ChapterSection(SectionType.EXPERIENCE);
    private ChapterSection education = new ChapterSection(SectionType.EDUCATION);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString() , fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }
}
