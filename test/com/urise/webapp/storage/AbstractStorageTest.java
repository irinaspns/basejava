package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {

    protected Storage storage;
    protected static final Resume RESUME_1 = new Resume("uiid1");
    protected static final Resume RESUME_2 = new Resume("uiid2");
    protected static final Resume RESUME_3 = new Resume("uiid3");
    protected static final Resume RESUME_DUMMY = new Resume("DUMMY");

    protected static final int EXPECTED_SIZE = 3;

    public AbstractStorageTest(Storage storage) {
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
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
       storage.update(RESUME_1);
        Assert.assertEquals(EXPECTED_SIZE, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void delete() {
        int sizeBeforeDelete = storage.size();
        storage.delete(RESUME_2.getUuid());
        Assert.assertEquals(sizeBeforeDelete - 1, storage.size());
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
    public void getAllSorted() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(storage.size(), resumes.size());
        Assert.assertEquals(RESUME_1, resumes.get(0));
        Assert.assertEquals(RESUME_2, resumes.get(1));
        Assert.assertEquals(RESUME_3,resumes.get(2));
    }

    @Test
    public void getSize() {
        Assert.assertEquals(EXPECTED_SIZE, storage.size());
    }
}