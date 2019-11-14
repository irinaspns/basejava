package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        subUpdate(checkExist(resume.getUuid()), resume);
    }

    public void save(Resume resume) {
        subSave(checkNotExist(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        subDelete(checkExist(uuid), uuid);
    }

    public Resume get(String uuid) {
        return subGet(checkExist(uuid), uuid);
    }

    private Object checkExist(String uuid) {
        Object searchKey = findElement(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object checkNotExist(String uuid) {
        Object searchKey = findElement(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private boolean isExist(Object searchKey) {
        return isSubIsExist(searchKey);
    }

    protected abstract boolean isSubIsExist(Object searchKey);

    protected abstract Object findElement(String uuid);

    protected abstract void subUpdate(Object searchKey, Resume resume);

    protected abstract void subSave(Object searchKey, Resume resume);

    protected abstract Resume subGet(Object searchKey, String uuid);

    protected abstract void subDelete(Object searchKey, String uuid);

    protected int getInteger(Object searchKey) {
        return searchKey == null ? -1 : (Integer) searchKey;
    }
}
