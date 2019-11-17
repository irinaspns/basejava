package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(("NAAM_" + i)));
            }
        } catch (StorageException se) {
            Assert.fail();
        }
        storage.save(RESUME_DUMMY);
    }

    @Test
    @Override
    public void getAllSorted() {
        List<Resume> expectedList = new ArrayList<>();
        expectedList.add(RESUME_1);
        expectedList.add(RESUME_2);
        expectedList.add(RESUME_3);

        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(storage.size(), resumes.size());
        Assert.assertEquals(expectedList, resumes);
    }
}