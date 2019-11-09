package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            subUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            subSave(index, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            subDelete(index, uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return subGet(index, uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int getIndex(String uuid);
    protected abstract void subUpdate(int index, Resume resume);
    protected abstract void subSave(int index, Resume resume);
    protected abstract Resume subGet(int index, String uuid);
    protected abstract void subDelete(int index, String uuid);
}
