package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Map based com.urise.webapp.storage for Resumes
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int getSize() {
        return storage.size();
    }

    protected void subUpdate(Object searchKey, Resume resume) {
        subSave(getString(searchKey), resume);
    }

    protected void subSave(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    protected void subDelete(Object searchKey, String uuid) {
        storage.remove(uuid);
    }

    protected Resume subGet(Object searchKey, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected Object findElement(String uuid) {
        return storage.get(uuid) == null ? null : uuid;
    }

}
