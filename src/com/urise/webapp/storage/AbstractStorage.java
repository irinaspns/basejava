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
        int position = getPosition(resume.getUuid());
        if (position < 0) {
            subSave(position, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        subDelete(checkExist(uuid), uuid);
    }

    public Resume get(String uuid) {
        return subGet(checkExist(uuid), uuid);
    }

    private int checkExist(String uuid) {
        int position = getPosition(uuid);
        if (position >= 0) {
            return position;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int getPosition(String uuid);

    protected abstract void subUpdate(int position, Resume resume);

    protected abstract void subSave(int position, Resume resume);

    protected abstract Resume subGet(int position, String uuid);

    protected abstract void subDelete(int position, String uuid);
}
