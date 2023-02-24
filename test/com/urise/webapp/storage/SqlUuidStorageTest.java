package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlUuidStorageTest extends AbstractStorageTest {

    public SqlUuidStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword()));
    }
}
