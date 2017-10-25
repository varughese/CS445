public class SelectionSorter implements IntSorter {
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
		for(int k=0; k<array.length-1; k++) {
			int minIndex = k;
			for(int i=k+1; i<array.length; i++) {
				if(array[i] < array[minIndex]) {
					minIndex = i;
				}
			}
			swap(array, k, minIndex);
		}
		endTime = System.nanoTime();
	}

	  private void swap(int[] values, int p1, int p2) {
	    int temp = values[p1];
	    values[p1] = values[p2];
	    values[p2] = temp;
	    moves++;
	  }
	
	public int getMoves() {
		return moves;
	}

	public long getSortTime() {
		return endTime - startTime;
	}

}
