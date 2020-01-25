package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTestOld {

    public FileStorageTest() {
        super(new FileStorage(AbstractStorageTestOld.STORAGE_DIR, new ObjectStreamSerializer()));
    }
}