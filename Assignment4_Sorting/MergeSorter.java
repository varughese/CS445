import java.util.Arrays;

public class MergeSorter implements IntSorter {
	private int[] array = null;
	private int moves = 0;
	private long startTime = 0;
	private long endTime = 0;
	
	public void init(int[] a) {
		this.array = a;
		moves = 0;
	}

	public void mergesort(int[] values, int[] temp, int start, int end) {
		int mid = (start+end)/2;

		if(start != end) {
			mergesort(values, temp, start, mid);
			mergesort(values, temp, mid+1, end);			
		}
		merge(values, temp, start, mid, end);
	}
	
	public void merge(int[] values, int[] temp, int start, int mid, int end) {
		int left = start;
		int right = mid;
		int index = 0;
		while(left < mid && right < end) {
			if(values[left] <= values[right]) {
				temp[index] = values[left];
				left++;
			} else {
				temp[index] = values[right];
				right++;
			}
			moves++;
			index++;
		}
	
		while(left < mid) {
			temp[index++] = values[left++];
			moves++;
		}
		while(right < end) {
			temp[index++] = values[right++];
			moves++;
		}
		
		System.out.println("T::: " + Arrays.toString(temp));
		System.out.println("Start:: " + start + " End:: " + end);
		
		System.arraycopy(temp, 0, values, start, index);
		temp = new int[values.length];
		System.out.println("A::: " + Arrays.toString(values));
		System.out.println();
	}
	
	public void sort() {
		startTime = System.nanoTime();
		mergesort(array, new int[array.length], 0, array.length);
		endTime = System.nanoTime();
	}

	public int getMoves() {
		return moves;
	}

	public long getSortTime() {
		return endTime - startTime;
	}
}