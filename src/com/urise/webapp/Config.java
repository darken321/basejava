package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {
    // текущий каталог .\\config\\resumes.properties
    // или                config\\resumes.properties
    protected static final File PROPS = new File(getHomeDir(), "config/resumes.properties");
    //  protected static final File PROPS = new File("E:\\java\\Projects\\BaseJava\\config\\resumes.properties");
//    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final Config INSTANCE = new Config();
    private final Properties props = new Properties();
    private final File storageDir;
    private Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);

            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
//            storage = new SqlStorage("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

        } catch (IOException e) {

            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private static File getHomeDir() {
        //String prop = System.getProperty("homeDir");
        //File homeDir = new File(prop == null ? "." : prop);
//        if (!homeDir.isDirectory()) {
//            throw new IllegalStateException(homeDir + " is not a directory");
//        }
      File homeDir = new File("E:/java/Projects/BaseJava");
        //String prop = System.getProperty("user.home");
        return homeDir;
    }
//диагностический класс
//    private static File getHomeDir() {
//         String prop = System.getProperty("user.home");
//        if (prop == null) {
//            throw new IllegalStateException("prop is null");
//        } else {
//            throw new IllegalStateException(prop + "prop is not null");
//        }
//    }
}
