package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTestOld {

    public PathStorageTest() {
        super(new PathStorage(AbstractStorageTestOld.STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}