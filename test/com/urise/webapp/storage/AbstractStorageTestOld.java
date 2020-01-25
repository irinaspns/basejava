package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.SectionType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTestOld {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
//    protected static final File STORAGE_DIR = new File("C:\\Workspaces\\IDEA-IZO_BASE\\basejava\\storage");

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
//        ResumeTestData.addContacts(RESUME_1);
//        ResumeTestData.addSections(RESUME_1);
        RESUME_2 = new Resume(UUID_2, "Name2");
//        RESUME_2.addSection(SectionType.ACHIEVEMENT, ResumeTestData.addAchievements());
        RESUME_3 = new Resume(UUID_3, "Name3");
//        RESUME_3.addSection(SectionType.EDUCATION, ResumeTestData.addEducations());
//        RESUME_3.addSection(SectionType.EXPERIENCE, ResumeTestData.addExperiences());
        RESUME_4 = new Resume(UUID_4, "Name4");
//        RESUME_4.addSection(SectionType.QUALIFICATIONS, ResumeTestData.addQualifications());
//        RESUME_4.addSection(SectionType.PERSONAL, ResumeTestData.addPersonal());
    }

    AbstractStorageTestOld(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        Resume result =  storage.get(r.getUuid());
        assertEquals(r.getUuid(), result.getUuid());
        assertEquals(r.getFullName(), result.getFullName());
        assertEquals(r.getContacts(), result.getContacts());
        Map<SectionType, Section> in = r.getSections();
        Map<SectionType, Section> out = r.getSections();

        for (Map.Entry<SectionType, Section> entry: in.entrySet()) {
            SectionType type = entry.getKey();
            Section section = entry.getValue();

            if (out.get(type) != section) {
                System.out.println("fout");
            }
        }
        assertEquals(r.getSections(), result.getSections());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}