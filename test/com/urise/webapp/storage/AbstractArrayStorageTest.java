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
    private static final Resume RESUME_1 = new Resume("uiid1");
    private static final Resume RESUME_2 = new Resume("uiid2");
    private static final Resume RESUME_3 = new Resume("uiid3");
    private static final Resume RESUME_DUMMY = new Resume("DUMMY");
    private static final int EXPECTED_SIZE = 3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test
    public void update() {
       storage.update(RESUME_1);
        Assert.assertEquals(EXPECTED_SIZE, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.getSize());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.getSize(); i < LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException se) {
            Assert.fail();
        }
        storage.save(RESUME_DUMMY);
    }

    @Test
    public void delete() {
        int sizeBeforeDelete = storage.getSize();
        storage.delete(RESUME_2.getUuid());
        Assert.assertEquals(sizeBeforeDelete - 1, storage.getSize());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_DUMMY.getUuid());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_DUMMY.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertNotNull(resumes);
        Assert.assertEquals(storage.getSize(), resumes.length);
        Assert.assertEquals(RESUME_1, resumes[0]);
        Assert.assertEquals(RESUME_2, resumes[1]);
        Assert.assertEquals(RESUME_3, resumes[2]);
    }

    @Test
    public void getSize() {
        Assert.assertEquals(EXPECTED_SIZE, storage.getSize());
    }
}