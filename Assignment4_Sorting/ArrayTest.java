import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.*;


public class ArrayTest {

  int[] array = createArray(2000);
  int[] arrayCopy;
  IntSorter sorter;
  
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
  

  @Before
  public void initObjects() {
	  arrayCopy = Arrays.copyOf(array, array.length);
  }
  
  @Test
  public void testBubbleSort() {
    sorter = new BubbleSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.println("\nBubble");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testInsertionSort() {
    sorter = new InsertionSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.println("\nInsertion");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testSelectionSort() {
    sorter = new SelectionSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.println("\nSelection");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testMergeSort() {
    sorter = new MergeSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.println("\nMerge");
    assertTrue(isSorted(arrayCopy));
  }
  
  
  @After
  public void getSortTime() {
	  long sortTime = sorter.getSortTime();
	  DecimalFormat myFormatter = new DecimalFormat("###,###,###");
      String output = myFormatter.format(sortTime);
	  System.out.println("----Time Taken: " + output + " ns");
  }
  
  @After
  public void getSortMoves() {
	  int moves = sorter.getMoves();
	  DecimalFormat myFormatter = new DecimalFormat("###,###,###");
      String output = myFormatter.format(moves);
	  System.out.println("----Moves: " + output + " moves");
  }
}
