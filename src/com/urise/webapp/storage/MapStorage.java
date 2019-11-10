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

    protected void subUpdate(int index, Resume resume) {
        subSave(index, resume);
    }

    protected void subSave(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    protected void subDelete(int index, String uuid) {
        storage.remove(uuid);
    }

    protected Resume subGet(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected int getPosition(String uuid) {
        return storage.get(uuid) == null ? -1 : 0;
    }

}
