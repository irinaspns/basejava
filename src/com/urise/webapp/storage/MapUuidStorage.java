package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Map based com.urise.webapp.storage for Resumes
 */
public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put(searchKey.toString(), resume);
    }

    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
        return storage.get(uuid) == null ? null : uuid;
    }
}
