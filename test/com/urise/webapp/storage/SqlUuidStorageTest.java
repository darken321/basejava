package com.urise.webapp.storage;

public class SqlUuidStorageTest extends AbstractStorageTest {

    public SqlUuidStorageTest() {
        //TODO удалить комменты
//        String dbUrl = Config.get().getDbUrl().getName();
//        String dbUser = Config.get().getDbUser().getName();
//        String dbPassword = Config.get().getDbPassword().getName();

        super(new SqlStorage("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin"));
    }
}
