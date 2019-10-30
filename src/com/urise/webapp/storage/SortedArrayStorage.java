package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public void update(Resume resume) {
        super.save(resume);
        sort();
    }

    public void save(Resume resume) {
        super.save(resume);
        sort();
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            Resume[] first = Arrays.copyOfRange(storage, 0, index);
            Resume[] second = Arrays.copyOfRange(storage, index + 1, size);
            storage = concat(first, second);
            size--;
        } else {
            System.out.println("Nothing to delete. No Resume with uuid = " + uuid + " in storage.");
        }
    }

    private void sort() {
        for (int i = 1; i < size; i++) {
            Resume resume = storage[i];
            // Find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(storage, 0, i, resume) + 1);
            //Shifting array to one location right
            System.arraycopy(storage, j, storage, j + 1, i - j);
            //Placing element at its correct location
            storage[j] = resume;
        }
    }

    private Resume[] concat(Resume[] first, Resume[] second) {
        Resume[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
