package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String TEST_UUID = "dummy";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size(), "Wrong size");
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size(), "Wrong size after clear");
    }

    @Test
    void update() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(new Resume(TEST_UUID));
        }, "update error");
    }

    @Test
    void saveOverflow() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(TEST_UUID + (i + 1)));
            }
        } catch (Exception ArrayIndexOutOfBoundsException) {
            Assertions.fail("Unexpected array overflow");
        }
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume(TEST_UUID));
        }, "save overflow error");
    }


    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_2));
        }, "save exist error");
    }

    @Test
    void save() {
        Resume r = new Resume(TEST_UUID);
        storage.save(r);
        Assertions.assertEquals(storage.get(TEST_UUID), r, "get error");
        Assertions.assertEquals(storage.size(), 4, "Wrong size after save");
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(TEST_UUID);
        }, "not exist delete error");
    }

    @Test
    void delete() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_2);
            storage.get(UUID_2);
        }, "deletion error");
        Assertions.assertEquals(storage.size(), 2, "Wrong size after delete");
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(TEST_UUID);
        }, "get not exist error");
    }

    @Test
    void get() {
        Assertions.assertEquals(new Resume(UUID_3), storage.get(UUID_3), "get error");
    }

    @Test
    void getAll() {
    }
}