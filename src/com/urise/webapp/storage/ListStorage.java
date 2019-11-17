package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * List based com.urise.webapp.storage for Resumes
 */
public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(Resume resume) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(resume)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Comparator<Resume> comparator
                = Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid);

        Collections.sort(list, comparator);
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}