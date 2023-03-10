package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlUuidStorageTest extends AbstractStorageTest {

    public SqlUuidStorageTest() {
        super(Config.get().getStorage());
    }
}
