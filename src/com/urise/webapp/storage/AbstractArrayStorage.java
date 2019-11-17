package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
//    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.stream(Arrays.copyOfRange(storage, 0, size))
                .sorted(Comparator.comparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume);
        } else {
            insertElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    public void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected List<Resume> getAll() {
        return Arrays.stream(Arrays.copyOfRange(storage, 0, size))
                .collect(Collectors.toList());
    }

    @Override
    protected Comparator getComparator() {
        return UUID_COMPARATOR;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract Integer getSearchKey(Resume resume);
}
