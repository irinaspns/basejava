package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_DUMMY = "uuidDummy";

    private static final String NAAM_1 = "Zarubina O";
    private static final String NAAM_2 = "Laboda S";
    private static final String NAAM_3 = "Antonov J";
    private static final String NAAM_4 = "Neznaju T";
    private static final String NAAM_DUMMY = "Undefined W";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    protected static final Resume RESUME_DUMMY;

    static {
        RESUME_1 = new Resume(UUID_1, NAAM_1);
        RESUME_2 = new Resume(UUID_2, NAAM_2);
        RESUME_3 = new Resume(UUID_3, NAAM_3);
        RESUME_4 = new Resume(UUID_4, NAAM_4);
        RESUME_DUMMY = new Resume(UUID_DUMMY, NAAM_DUMMY);
    }

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_1);
        storage.save(RESUME_2);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        assertSame(RESUME_1, storage.get(RESUME_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get(RESUME_DUMMY);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedList = new ArrayList<>();
        expectedList.add(RESUME_3);
        expectedList.add(RESUME_2);
        expectedList.add(RESUME_1);

        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(storage.size(), resumes.size());
        Assert.assertEquals(expectedList, resumes);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_1);
        assertSize(2);
        storage.get(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_DUMMY);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_DUMMY);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}