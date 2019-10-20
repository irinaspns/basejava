import java.util.Arrays;
import java.util.Collections;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage,null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid != null &&  storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int removeIndex = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid != null &&  storage[i].uuid.equals(uuid)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex >= 0) {
            for(int i = removeIndex; i < storage.length -1; i++){
                storage[i] = storage[i + 1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int lastFilledIndex = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                lastFilledIndex = i;
            } else {
                break;
            }
        }
        return Arrays.copyOf(storage, ++lastFilledIndex);
    }

    int size() {
        int storageSize = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storageSize = i;
            } else {
                break;
            }
        }
        return ++storageSize;
    }
}
