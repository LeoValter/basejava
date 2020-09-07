package ru.leovalter.basejava.storage;

import ru.leovalter.basejava.exception.ExistStorageException;
import ru.leovalter.basejava.exception.NotExistStorageException;
import ru.leovalter.basejava.exception.StorageException;
import ru.leovalter.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}