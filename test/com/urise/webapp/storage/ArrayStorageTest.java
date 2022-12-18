package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayStorageTest extends AbstractArrayStorageTest {
    protected ArrayStorageTest() {
        super(new ArrayStorage());
    }
    @Test
    void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(TEST_UUID + (i + 1)));
            }
        } catch (Exception ArrayIndexOutOfBoundsException) {
            Assertions.fail("Unexpected array overflow");
        }
        assertSize(AbstractArrayStorage.STORAGE_LIMIT);
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(RESUME_NOT_EXIST);
        }, "save overflow error");
    }
}
