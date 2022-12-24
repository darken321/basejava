package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    final Storage storage;
    static final String UUID_1 = "uuid1";
    static final String UUID_2 = "uuid2";
    static final String UUID_3 = "uuid3";

    static final String NAME_1 = "name1";
    static final String NAME_2 = "name2";
    static final String NAME_3 = "name3";
    static final String TEST_UUID = "dummy";

    final static Resume RESUME_1 = new Resume(UUID_1, NAME_1);
    final static Resume RESUME_2 = new Resume(UUID_2, NAME_2);
    final static Resume RESUME_3 = new Resume(UUID_3, NAME_3);
    final static Resume RESUME_NOT_EXIST = new Resume(TEST_UUID,TEST_UUID);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertEquals(storage.getAllSorted(), new ArrayList<Resume>());
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_NOT_EXIST);
        }, "update error");
    }

    @Test
    void update() {
        Resume r = new Resume(UUID_2,NAME_3);
        storage.update(r);
        Assertions.assertSame(r, storage.get(UUID_2));
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_2);
        }, "save exist error");
    }

    @Test
    void save() {
        storage.save(RESUME_NOT_EXIST);
        assertGet(RESUME_NOT_EXIST);
        assertSize(4);
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(TEST_UUID);
        }, "not exist delete error");
    }

    @Test
    void delete() {
        storage.delete(UUID_2);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_2);
        }, "deletion error");
        assertSize(2);
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(TEST_UUID);
        }, "get not exist error");
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getAllSorted() {
        List<Resume> expected = new ArrayList<>();
        expected.add(RESUME_1);
        expected.add(RESUME_2);
        expected.add(RESUME_3);
        Assertions.assertEquals(expected, storage.getAllSorted());
        assertSize(3);
    }

    protected void assertSize(int size) {
        Assertions.assertEquals(size, storage.size(), "Wrong size");
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(r, storage.get(r.getUuid()), "get error");
    }
}