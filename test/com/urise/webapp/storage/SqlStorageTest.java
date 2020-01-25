package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTestOld {
    private static Config CONFIG = Config.get();

    public SqlStorageTest() {
        super(new SqlStorage(CONFIG.getDbUrl(), CONFIG.getDbUser(), CONFIG.getDbPassword()));
    }
}
