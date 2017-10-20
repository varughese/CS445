import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayTest {

  public static int[] createArray(int size) {
    int[] result = new int[size];
    for (int i = 0; i < result.length; i++) {
      result[i] = (int)(Math.random() * 1000);
    }
    return result;
  }
  
  public static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }
  
  @Test
  public void testSort() {
    int[] array = createArray(100);
    BubbleSorter sorter = new BubbleSorter();
    sorter.init(array);
    sorter.sort();
    System.out.println("\nBubble Moves: " + sorter.getMoves());
    assertTrue(isSorted(array));
  }
  
  @Test
  public void testInsertionSort() {
    int[] array = createArray(100);
    InsertionSorter sorter = new InsertionSorter();
    sorter.init(array);
    sorter.sort();
    System.out.println("\nInsertion Moves: " + sorter.getMoves());
    assertTrue(isSorted(array));
  }
  
  @Test
  public void testSelectinoSort() {
    int[] array = createArray(100);
    SelectionSorter sorter = new SelectionSorter();
    sorter.init(array);
    sorter.sort();
    System.out.println("\nSelection Moves: " + sorter.getMoves());
    assertTrue(isSorted(array));
  }
  
  @Test
  public void testMergeSort() {
    int[] array = {9, 8, 7, 6};
    MergeSorter sorter = new MergeSorter();
    sorter.init(array);
    sorter.sort();
    System.out.println("\nMerge moves: " + sorter.getMoves());
    assertTrue(isSorted(array));
  }
}
