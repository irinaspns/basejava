package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findElement(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insert(int index, Resume resume) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void fillElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
