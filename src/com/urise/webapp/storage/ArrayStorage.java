package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object findElement(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    @Override
    protected void insert(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void fillElement(int index) {
        storage[index] = storage[size - 1];
    }
}
