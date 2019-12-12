package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;

    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }
}
