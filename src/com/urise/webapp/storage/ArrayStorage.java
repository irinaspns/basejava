package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int LIMIT = 10_000;
    private Resume[] storage = new Resume[LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int position = getPositionInStorage(resume.getUuid());
        if (position >= 0) {
            storage[position] = resume;
        } else {
            System.out.println("Resume can not be updated. It's not found in storage. uuid = " + resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int position = getPositionInStorage(resume.getUuid());
        if (position < 0) {
            if (size < storage.length) {
                storage[size++] = resume;
            } else {
                System.out.println("Limit of storage has been reached.");
            }
        }
    }

    public Resume get(String uuid) {
        int position = getPositionInStorage(uuid);
        if (position >= 0) {
            return storage[position];
        }
        System.out.println("There is no Resume with uuid = " + uuid + " in storage.");
        return null;
    }

    public void delete(String uuid) {
        int position = getPositionInStorage(uuid);
        if (position >= 0) {
            if (size - 1 - position >= 0) {
                System.arraycopy(storage, position + 1, storage, position, size - 1 - position);
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Nothing to delete. No Resume with uuid = " + uuid + " in storage.");
        }
    }

    /**
     * @return array, contains only Resumes in com.urise.webapp.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getPositionInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
