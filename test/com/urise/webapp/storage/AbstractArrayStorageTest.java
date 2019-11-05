package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.LIMIT;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uiid1";
    private static final String UUID_2 = "uiid2";
    private static final String UUID_3 = "uiid3";
    private static final String DUMMY = "DUMMY";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 0; i < LIMIT - 3; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException se) {
            Assert.fail();
        }
        storage.save(new Resume(DUMMY));
    }

    @Test
    public void delete() {
        int sizeBeforeDelete = storage.getSize();
        storage.delete(UUID_2);
        Assert.assertEquals(sizeBeforeDelete - 1, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertNotNull(resumes);
        Assert.assertEquals(storage.getSize(), resumes.length);
    }

    @Test
    public void getSize() {
        Assert.assertEquals(3, storage.getSize());
    }
}