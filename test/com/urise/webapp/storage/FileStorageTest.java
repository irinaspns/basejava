package com.urise.webapp.storage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(AbstractStorageTest.STORAGE_DIR, new ObjectStreamStrategy()));
    }
}