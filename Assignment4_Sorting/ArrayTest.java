import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.*;


public class ArrayTest {

  int[] array = createArray(50000);
  int[] arrayCopy;
  IntSorter sorter;
  DecimalFormat myFormatter = new DecimalFormat("###,###,###");
  
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
    System.out.print("\nBubble   ");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testInsertionSort() {
    sorter = new InsertionSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.print("\nInsertion");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testSelectionSort() {
    sorter = new SelectionSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.print("\nSelection");
    assertTrue(isSorted(arrayCopy));
  }
  
  @Test
  public void testMergeSort() {
    sorter = new MergeSorter();
    sorter.init(arrayCopy);
    sorter.sort();
    System.out.print("\nMerge      ");
    assertTrue(isSorted(arrayCopy));
  }
  
  
  @After
  public void getSortTime() {
	  long sortTime = sorter.getSortTime();	  
      String output = myFormatter.format(sortTime);
	  System.out.print("\tTime Taken: " + output + " ns\t");
  }
  
  @After
  public void getSortMoves() {
	  int moves = sorter.getMoves();
      String output = myFormatter.format(moves);
	  System.out.print("\tMoves: " + output + " moves");
  }
}
