package ru.leovalter.basejava.storage;

import ru.leovalter.basejava.Config;


public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}