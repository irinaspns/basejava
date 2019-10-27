package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume resume) {
        int position = positionInStorage(resume.getUuid());
        if (position >= 0) {
            storage[position] = resume;
        } else {
            System.out.println("Resume can not be updated. It's not found in storage. uuid = " + resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int position = positionInStorage(resume.getUuid());
        if (position < 0) {
            if (size < storage.length) {
                storage[size++] = resume;
            } else {
                System.out.println("Limit of storage has been reached.");
            }
        }
    }

    public Resume get(String uuid) {
        int position = positionInStorage(uuid);
        if (position >= 0) {
            return storage[position];
        }
        System.out.println("There is no Resume with uuid = " + uuid + " in storage.");
        return null;
    }

    public void delete(String uuid) {
        int position = positionInStorage(uuid);
        if (position >= 0) {
            for (int i = position; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
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

    private int positionInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
