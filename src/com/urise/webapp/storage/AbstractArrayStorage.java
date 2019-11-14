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

    protected void subUpdate(Object searchKey, Resume resume) {
        storage[getInteger(searchKey)] = resume;
    }

    protected void subSave(Object searchKey, Resume resume) {
        if (size == LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insert(getInteger(searchKey), resume);
        size++;
    }

    protected void subDelete(Object searchKey, String uuid) {
        fillElement(getInteger(searchKey));
        storage[size - 1] = null;
        size--;
    }

    protected Resume subGet(Object searchKey, String uuid) {
        return storage[getInteger(searchKey)];
    }

    @Override
    protected boolean isSubIsExist(Object searchKey) {
        if (searchKey == null || searchKey.toString().compareTo("0") < 0) {
            return false;
        }
        return true;
    }

    protected abstract void fillElement(int index);

    protected abstract void insert(int index, Resume resume);
}
