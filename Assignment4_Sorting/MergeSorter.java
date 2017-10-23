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
		if(start == end) return;
		
		int mid = (start+end)/2;

		mergesort(values, temp, start, mid);
		mergesort(values, temp, mid+1, end);			
		merge(values, temp, start, mid, end);
	}
	
	public void merge(int[] values, int[] temp, int start, int mid, int end) {
		int left = start;
		int right = mid+1;
		int index = 0;
		while(left <= mid && right <= end) {
			if(values[left] <= values[right]) {
				temp[index] = values[left];
				left++;
			} else {
				temp[index] = values[right];
				right++;
			}
			index++;
		}
	
		while(left <= mid) {
			temp[index++] = values[left++];
		}
		
		while(right <= end) {
			temp[index++] = values[right++];
		}
		
		moves += index;
		System.arraycopy(temp, 0, values, start, index);	
	}
	
	public void sort() {
		startTime = System.nanoTime();
		mergesort(array, new int[array.length], 0, array.length-1);
		endTime = System.nanoTime();
	}

	public int getMoves() {
		return moves;
	}

	public long getSortTime() {
		return endTime - startTime;
	}
}