public class HashSet<T> {      

  private static final int DEFAULT_CAPACITY = 23;
  private static final double LOAD_FACTOR = 0.5;
  private Object[] table;
  private int size = 0;
  
  public HashSet() {
    this(DEFAULT_CAPACITY);
  }
  
  public HashSet(int initialCapacity) {
    table = new Object[initialCapacity];
  }
  
  public int size() {
    return size;
  }
  
  
  public void print() {
    for (int i = 0; i < table.length; i++) {
      System.out.print(i + ": ");
      System.out.println(table[i]);
    }
  }

  public boolean contains(T value) {
    int position = getAddress(value);
    for (int i = 0; i < table.length; i++) {
      int index = (position + i) % table.length;
      if (table[index] == null) {
        break;
      } else if (value.equals(table[index])) {
        return true;
      }
    }
    return false;
  }
  
  public T remove(T value) {
    int position = getAddress(value);
    T result = null;
    for (int i = 0; i < table.length; i++) {
      int index = (position + i) % table.length;
      if (table[index] == null) {
        // we have found a blank spot
        break;
      } else if (value.equals(table[index])) {
        // we have found the thing to remove
        result = (T)table[index];
        table[position] = null;
        size--;
      } else if (result != null) {
        // we already have removed something, so keep readdressing things until we get to a null value
        T readd = (T)table[index];
        table[index] = null;
        size--;
        this.add(readd);
      }
    }
    return result;
  }

  public void add(T value) {
    if (size > table.length * LOAD_FACTOR) {
      // if we have more things here than the LOAD_FACTOR allows, let's readdress everything.
      readdress();
    }
    
    int position = getAddress(value);
    for (int i = 0; i < table.length; i++) {
      int index = (position + i) % table.length;
      if (table[index] == null) {
        table[index] = value;
        size++;
        break;
      } else if (table[index].equals(value)) {
        table[index] = value;
        break;
      }
    }
  }
  
  private int getAddress(T value) {
    return Math.abs(value.hashCode()) % table.length;
  }
  
  private void readdress() {
    // create a new array, and re-add everything from the old array.
    Object[] temp = table;
    table = new Object[table.length * 2 + 1];
    size = 0;
    for (int i = 0; i < temp.length; i++) {
      T current = (T)temp[i];
      if (current != null) {
        this.add(current);
      }
    }
  }
}
