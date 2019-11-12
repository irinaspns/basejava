package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int LIMIT = 10_000;
    protected Resume[] storage = new Resume[LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }

    protected void subUpdate(Object obj, Resume resume) {
        storage[getInteger(obj)] = resume;
    }

    protected void subSave(Object obj, Resume resume) {
        if (size == LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insert(getInteger(obj), resume);
        size++;
    }

    protected void subDelete(Object obj, String uuid) {
        fillElement(getInteger(obj));
        storage[size - 1] = null;
        size--;
    }

    protected Resume subGet(Object obj, String uuid) {
        return storage[getInteger(obj)];
    }

    protected abstract void fillElement(int index);

    protected abstract void insert(int index, Resume resume);
}
