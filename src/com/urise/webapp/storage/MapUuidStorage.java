package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Map based com.urise.webapp.storage for Resumes
 */
public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Comparator<Resume> comparator
                = Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid);

        return storage.entrySet().stream()
                .map(e -> e.getValue())
                .sorted(comparator)
                .collect(Collectors.toList());
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
    protected String getSearchKey(Resume resume) {
        return storage.get(resume.getUuid()) == null ? null : resume.getUuid();
    }
}
