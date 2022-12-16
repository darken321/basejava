package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapNameStorageTest extends AbstractArrayStorageTest {

    public MapNameStorageTest() {
        super(new MapNameStorage());
    }

    @Test
    void update() {
        Resume r = new Resume(UUID_2,NAME_3);
        storage.update(r);
        Assertions.assertSame(r, storage.get(NAME_3));
    }

    @Test
    void delete() {
        storage.delete(NAME_2);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(NAME_2);
        }, "deletion error");
        assertSize(2);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void saveOverflow() {
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(r, storage.get(r.getFullName()), "get error");
    }
}
