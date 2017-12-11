/*
 * For this assignment, your goal is to complete a MaxHeap and implement a heapsort algorithm.  A mostly-implemented MaxHeap has been provided for you.

25 points: code compiles
15 points: working implementation of heapsort
5 points: proper implementation of remove method
5 points: grader's discretion
 */

public class MaxHeap {

  private static final int DEFAULT_CAPACITY = 20;
  private int nextPosition = 0;
  private long[] values;
  
  public MaxHeap() {
    values = new long[DEFAULT_CAPACITY];
  }
  
  public MaxHeap(int initialCapacity) {
    values = new long[initialCapacity];
  }
  
  public MaxHeap(long[] array) {
    this.values = array;
    for (int i = 1; i < values.length; i++) {
      reheapUp(i);
    }
    nextPosition = values.length;
  }
  
  public static void heapsort(long[] array) {
    MaxHeap heap = new MaxHeap(array);
    for(int i=0; i<array.length; i++) {
    		heap.remove();
    }
  }
  
  public boolean isEmpty() {
    return nextPosition == 0;
  }
  
  public void add(long value) {
    ensureCapacity();
    values[nextPosition] = value;
    reheapUp(nextPosition);
    nextPosition++;
  }
  
  public long getMax() {
    if (this.isEmpty()) {
      throw new UnsupportedOperationException("Heap is empty.");
    }
    return values[0];
  }
  
  public long remove() {
	  if (this.isEmpty()) {
		  throw new UnsupportedOperationException("Heap is empty.");
	  }
	  nextPosition--;
	  swap(0, nextPosition);
	  reheapDown(0);
	  return values[nextPosition];
	  
  }
  
  private void reheapDown(int i) {
    int maxChild = getMaxChildIndex(i);
    if (maxChild > -1) {
      if (values[i] < values[maxChild]) {
        swap(i, maxChild);
        reheapDown(maxChild);
      }
    }
  }
  
  private void reheapUp(int i) {
    int parent = (i - 1) / 2;
    if (parent >= 0) {
      if (values[parent] < values[i]) {
        swap(i, parent);
        reheapUp(parent);
      }
    }
  }
    
  private void ensureCapacity() {
    if (nextPosition >= values.length) {
      long[] temp = new long[values.length * 2 + 1];
      System.arraycopy(values, 0, temp, 0, values.length);
      values = temp;
    }
  }
  
  private void swap(int a, int b) {
    long temp = values[a];
    values[a] = values[b];
    values[b] = temp;
  }

  private int getMaxChildIndex(int i) {
    int left = 2 * i + 1;
    if (left >= nextPosition) {
      return -1;
    } else {
      int right = 2 * i + 2;
      if (right >= nextPosition || values[left] > values[right]) {
        return left;
      } else {
        return right;
      }
    }
  }
}