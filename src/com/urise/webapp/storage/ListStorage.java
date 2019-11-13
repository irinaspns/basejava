package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * List based com.urise.webapp.storage for Resumes
 */
public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int getSize() {
        return storage.size();
    }

    protected void subUpdate(Object searchKey, Resume resume) {
        storage.set(getInteger(searchKey), resume);
    }

    protected void subSave(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    protected void subDelete(Object searchKey, String uuid) {
        storage.remove(getInteger(searchKey));
    }

    protected Resume subGet(Object searchKey, String uuid) {
        return storage.get(getInteger(searchKey));
    }

    @Override
    protected Object findElement(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        return index >= 0 ? Integer.valueOf(index) : null;
    }

}
