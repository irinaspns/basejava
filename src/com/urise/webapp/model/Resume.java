package com.urise.webapp.model;

import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void addContact(ContactType type, String  value) {
        contacts.put(type, value);
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
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
