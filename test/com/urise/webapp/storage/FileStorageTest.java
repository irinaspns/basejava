package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(AbstractStorageTest.STORAGE_DIR, new ObjectStreamSerializer()));
    }
}