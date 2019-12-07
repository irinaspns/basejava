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
    private Map<SectionType, Section> data = new HashMap<>();

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

    public Map<ContactType, String> getContacten() {
        return contacten;
    }

    public void addContact(ContactType type, String  value) {
        this.contacten.put(type, value);
    }

    public Map<SectionType, Section> getData() {
        return data;
    }

    public void addData(SectionType type, Section data) {
        this.data.put(type, data);
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
