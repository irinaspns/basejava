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

    protected void subUpdate(Object obj, Resume resume) {
        storage.set(getInteger(obj), resume);
    }

    protected void subSave(Object obj, Resume resume) {
        storage.add(resume);
    }

    protected void subDelete(Object obj, String uuid) {
        storage.remove(getInteger(obj));
    }

    protected Resume subGet(Object obj, String uuid) {
        return storage.get(getInteger(obj));
    }

    @Override
    protected Object findElement(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        return index >= 0 ? Integer.valueOf(index) : null;
    }

}
