// --== CS400 Project One File Header ==--
// Name: Ria Sharma
// CSL Username: ria
// Email: rsharma78@wisc.edu
// Lecture #: Florian Heimerl - MWF 1:20-2:10PM
// Notes to Grader: N/A
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  protected Pair<KeyType, ValueType>[] data;
  protected int capacity;
  protected int size;

  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    data = (Pair<KeyType, ValueType>[]) new Pair[capacity];
    size = 0;
  }

  public HashtableMap() { // with default capacity = 8
    this(8);
  }

  protected class Pair<K, V> {
    public K key;
    public V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  @Override
  public void put(KeyType key, ValueType value) throws IllegalArgumentException {
    // throw exceptions
    if (key == null || value == null || containsKey(key)) {
      throw new IllegalArgumentException();
    }
    // load factor
    if (loadFactor() >= 0.7) {
      grow();
    }
    // put function
    int index = findIndex(key);
    if (index != -1) {
      data[index].value = value;
    } else {
      index = hash(key);
      while (data[index] != null) {
        index = (index + 1) % capacity;
      }
      size++;
      data[index] = new Pair<>(key, value);
    }
  }

  @Override
  public ValueType get(KeyType key) {
    int index = findIndex(key);
    // throw exception
    if (index == -1) {
      throw new NoSuchElementException();
    }
    // get function
    return data[index].value;
  }

  @Override
  public boolean containsKey(KeyType key) {
    return findIndex(key) != -1;
  }

  @Override
  public ValueType remove(KeyType key) {
    int index = findIndex(key);
    // throw exception
    if (index == -1) {
      throw new NoSuchElementException();
    }
    // remove
    ValueType value = data[index].value;
    data[index] = null;
    size--;
    return value;
  }

  private int findIndex(KeyType key) {
    int index = hash(key);
    // find index
    while (data[index] != null && (data[index].key == null || !data[index].key.equals(key))) {
      index = (index + 1) % capacity;
    }
    // index not accessible
    if (data[index] == null || data[index].key == null) {
      return -1;
    }

    return index;
  }

  private int hash(KeyType key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  private double loadFactor() {
    return (double) size / capacity;
  }


  @SuppressWarnings("unchecked")
  private void grow() {
    Pair<KeyType, ValueType>[] old = data;
    capacity *= 2;
    data = (Pair<KeyType, ValueType>[]) new Pair[capacity];
    size = 0;
    for (Pair<KeyType, ValueType> pair : old) {
      if (pair != null) {
        put(pair.key, pair.value);
      }
    }
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getCapacity() {
    return capacity;
  }

  @Override
  public void clear() {
    size = 0;
    for (int i = 0; i < capacity; i++) {
      data[i] = null;
    }
  }
}
