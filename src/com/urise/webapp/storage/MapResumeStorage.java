package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map based com.urise.webapp.storage for Resumes
 */
public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    public void clear() {
        storage.clear();
    }

    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }

    protected void doUpdate(Resume resume, Resume searchKey) {
        storage.put(searchKey.getUuid(), resume);
    }

    protected void doSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}
