import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

  Resume[] storage = new Resume[10000];

  void clear() {
    Arrays.fill(storage, 0, size(), null);
  }

  void save(Resume r) {
    if (r == null) {
      throw new NullPointerException();
    }

    if (size() > storage.length) {
      throw new IndexOutOfBoundsException();
    }

    storage[size()] = r;
  }

  Resume get(String uuid) {
    int size = size();
    for (int i = 0; i < size; i++) {
      if (uuid.equals(storage[i].uuid)) {
        return storage[i];
      }
    }
    return null;
  }

  void delete(String uuid) {
    int size = size();
    for (int i = 0; i < size; i++) {
      if (uuid.equals(storage[i].uuid)) {
        System.arraycopy(storage, i + 1, storage, i, size - i - 1);
        storage[size - 1] = null;
        return;
      }
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size());
  }

  int size() {
    int size = 0;
    while (storage[size] != null) {
      size++;
    }
    return size;
  }
}
