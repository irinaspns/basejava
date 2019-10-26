package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.urise.webapp.storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    public int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume r) {
        int indexInStorage = positionInStorage(r.getUuid());
        if (indexInStorage >= 0) {
            storage[indexInStorage] = r;
        } else {
            System.out.println("Resume can not be updated. It's not found in storage. uuid = " + r.getUuid());
        }
    }

    public void save(Resume r) {
        int indexInStorage = positionInStorage(r.getUuid());
        if (indexInStorage < 0) {
            if (size < 1000) {
                storage[size++] = r;
            } else {
                System.out.println("Limit of storage (1000) has been reached.");
            }
        } else {
            // Or update(r) ?????
            System.out.println("Resume can not be saved. It's already in storage. uuid = " + r.getUuid());
        }
    }

    public Resume get(String uuid) {
        if (positionInStorage(uuid) >= 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("There is no Resume with uuid = " + uuid + " in storage.");
        }
        return null;
    }

    public void delete(String uuid) {
        if (positionInStorage(uuid) >= 0) {
            int removeIndex = -1;
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    removeIndex = i;
                    break;
                }
            }
            if (removeIndex >= 0) {
                for (int i = removeIndex; i < size - 1; i++) {
                    storage[i] = storage[i + 1];
                }
                storage[size - 1] = null;
                size--;
            }
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
