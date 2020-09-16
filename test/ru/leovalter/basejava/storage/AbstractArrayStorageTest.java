package ru.leovalter.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.leovalter.basejava.exception.ExistStorageException;
import ru.leovalter.basejava.exception.NotExistStorageException;
import ru.leovalter.basejava.exception.StorageException;
import ru.leovalter.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }


    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

}