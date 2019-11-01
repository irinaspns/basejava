package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            if (size < storage.length) {
                int insertIndex = -index - 1;
                System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
                storage[insertIndex] = resume;
                size++;
            } else {
                System.out.println("Limit of storage has been reached.");
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size-1] = null;
            size--;
        } else {
            System.out.println("Nothing to delete. No Resume with uuid = " + uuid + " in storage.");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
