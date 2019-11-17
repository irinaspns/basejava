package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> UUID_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    protected abstract Object getSearchKey(Resume resume);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> getAll();

    protected abstract Comparator getComparator();

    public List<Resume> getAllSorted() {
        List<Resume> resumes = getAll();
        resumes.sort(getComparator());
        return resumes;
    }

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume);
        doUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume);
        doSave(resume, searchKey);
    }

    public void delete(Resume resume) {
        Object searchKey = getExistedSearchKey(resume);
        doDelete(searchKey);
    }

    public Resume get(Resume resume) {
        Object searchKey = getExistedSearchKey(resume);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume);
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume);
        }
        return searchKey;
    }
}
