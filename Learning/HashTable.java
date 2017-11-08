public class HashTable<K,V> {

  private static final int DEFAULT_CAPACITY = 23;
  private static final double LOAD_FACTOR = 0.5;
  private Object[] table;
  private int size = 0;
  
  public HashTable() {
    this(DEFAULT_CAPACITY);
  }
  
  public HashTable(int initialCapacity) {
    table = new Object[initialCapacity];
  }
  
  public int size() {
    return size;
  }
  
  public void print() {
    for (int i = 0; i < table.length; i++) {
      System.out.print(i + ": ");
      if (table[i] != null) {
        Entry<K,V> current = (Entry<K,V>)table[i];
        while (current != null) {
          System.out.print("(" + current.key + ", " + current.value + ") => ");
          current = current.next;
        }
      }
      System.out.println();
    }
  }
  
  public boolean containsKey(K key) {
    int position = getAddress(key);
    Entry<K,V> current = (Entry<K,V>)table[position];
    int hc = key.hashCode();
    
    // let's iterate through the chain until we find a spot where this *can't* be or we reach the end.
    while (current != null && hc > current.key.hashCode()) {
      current = current.next;
    }
    
    if (current != null) {
      if (current.key.hashCode() == hc) {
        return true;
      }
    }
    return false;
  }
  
  public V get(K key) {
    int position = getAddress(key);
    Entry<K,V> current = (Entry<K,V>)table[position];
    int hc = key.hashCode();
    
    // let's iterate through the chain until we find a spot where this *can't* be or we reach the end.
    while (current != null && hc > current.key.hashCode()) {
      current = current.next;
    }
    
    if (current != null) {
      if (key.equals(current.key)) {
        return current.value;
      }
    }
    return null;
  }
  
  public V remove(K key) {
    int position = getAddress(key);
    Entry<K,V> current = (Entry<K,V>)table[position];
    if (current != null) {
      if (current.key.equals(key)) {
        V result = current.value;
        size--;
        table[position] = current.next;
        return result;
      } else {
        int hc = key.hashCode();
        while (current.hasNext() && hc > current.next.key.hashCode()) {
          current = current.next;
        }
        if (current.hasNext() && current.next.key.equals(key)) {
          V result = current.next.value;
          current.next = current.next.next;
          size--;
          return result;
        }
      }
    }
    return null;
  }

  public void put(K key, V value) {
    if (size > table.length * LOAD_FACTOR) {
      // if we have more things here than the LOAD_FACTOR allows, let's readdress everything.
      readdress();
    }
    int position = getAddress(key);
    if (table[position] == null) {
      // if nothing is there, put this there.
      table[position] = new Entry(key, value);
      size++;
    } else {

      int hc = key.hashCode();
      Entry<K,V> current = (Entry<K,V>)table[position];
      if (hc < current.key.hashCode()) {
        // if we're *before* the first key in the chain, we insert ahead of it.
        table[position] = new Entry(key, value, current);
        size++;
      } else if (key.equals(current.key)) {
        // just overwrite the value in this entry - cheaper than creating a new object for the same purpose
        current.value = value;
        // note that .equals is **EQUIVALENCE** so it's possible that key & entry.key are not **identical**
        current.key = key;
      } else {

        // iterate through the chain
        while (current.hasNext() && hc > current.next.key.hashCode()) {
          current = current.next;
        }
        
        // now we know that this this new key goes after current, but it might still *replace* current.next.
        if (current.hasNext() && current.next.key.equals(key)) {
          // just replace the current one
          current.next.key = key;
          current.next.value = value;
        } else {
          // add after current
          current.next = new Entry<K,V>(key, value, current.next);
          size++;
        }
      }
    }
  }
  
  private int getAddress(K value) {
    return Math.abs(value.hashCode()) % table.length;
  }
  
  private void readdress() {
    // create a new array, and re-add everything from the old array.
    Object[] temp = table;
    table = new Object[table.length * 2 + 1];
    size = 0;
    for (int i = 0; i < temp.length; i++) {
      Entry<K,V> current = (Entry<K,V>)temp[i];
      while (current != null) {
        this.put(current.key, current.value);
        current = current.next;
      }
    }
  }
  
  private class Entry<K,V> {
    Entry<K,V> next;
    K key;
    V value;
    
    public Entry(K key, V value, Entry<K,V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
    
    public Entry(K key, V value) {
      this(key, value, null);
    }
    
    public boolean hasNext() {
      return next != null;
    }
  }
}
