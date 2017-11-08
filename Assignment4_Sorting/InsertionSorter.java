public class InsertionSorter implements IntSorter {
	private int[] array = null;
	private int moves = 0;
	private long startTime = 0;
	private long endTime = 0;
	
	
	public void init(int[] a) {
		this.array = a;
		moves = 0;
	}

	public void sort() {
		startTime = System.nanoTime();
		for(int i=1; i < array.length; i++) {
			for(int k=i; k > 0; k--) {
				if(array[k] < array[k-1]) {
					swap(array, k, k-1);
				}
			}
		}
		endTime = System.nanoTime();
	}

	private void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}

	public long getSortTime() {
		return endTime - startTime;
	}

}
