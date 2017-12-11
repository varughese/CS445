import static org.junit.Assert.*;

import org.junit.Test;

public class MaxHeapTest {
	public static boolean isSorted(long[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void test() {
		long[] arr = { 4, 5, 7, 1, 3, 4, 5, 6, 6, 8, 9, 10, 11, 23 };
		MaxHeap.heapsort(arr);
		assertTrue(isSorted(arr));
	}

}
