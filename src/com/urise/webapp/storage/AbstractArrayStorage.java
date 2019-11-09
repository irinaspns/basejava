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

    protected void subSave(int index, Resume resume) {
        if (size == LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insert(index, resume);
        size++;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }

    protected void subUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    protected void subDelete(int index, String uuid) {
        fillElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected Resume subGet(int index, String uuid) {
        return storage[index];
    }

    protected abstract void fillElement(int index);

    protected abstract void insert(int index, Resume resume);
}
