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
        return (Resume[]) storage.toArray();
    }

    public int getSize() {
        return storage.size();
    }

    protected void subUpdate(int index, Resume resume) {
        storage.add(index, resume);
    }

    protected void subSave(int index, Resume resume) {
        storage.add(resume);
    }

    protected void subDelete(int index, String uuid) {
        storage.remove(index);
    }

    protected Resume subGet(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

}
