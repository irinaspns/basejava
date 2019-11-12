package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
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
        Object obj = findElement(uuid);
        if (obj != null) {
            return obj;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object checkNotExist(String uuid) {
        Object obj = findElement(uuid);
        if (obj == null) {
            return obj;
        }
        throw new ExistStorageException(uuid);
    }

    protected abstract Object findElement(String uuid);

    protected abstract void subUpdate(Object obj, Resume resume);

    protected abstract void subSave(Object obj, Resume resume);

    protected abstract Resume subGet(Object obj, String uuid);

    protected abstract void subDelete(Object obj, String uuid);

    protected int getInteger(Object object) {
        return object == null ? -1 : (Integer) object;
    }
    protected String getString(Object object) {
        return (String) object;
    }
}
