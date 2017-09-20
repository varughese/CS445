
public class StringArrayList implements StringList {
	private String[] arr;
	private int arrayLength;

	public StringArrayList() {
		arrayLength = 0;
		arr = new String[0];
	}
	
	private void ensureCapacity() {
		if(arrayLength == arr.length) {
			String[] temp = new String[arr.length * 2 +1];
			System.arraycopy(arr, 0, temp, 0, arr.length);
			arr = temp;		
		}
	}
	
	/**
	 * Throws IndexOutOfBounds if index is not in proper range.
	 * This method is called by add, get, remove.
	 */
	private void checkIndexIsInRange(int index) throws ArrayIndexOutOfBoundsException{
		if(index >= arrayLength) 
			throw new ArrayIndexOutOfBoundsException("The specifed index to add was greater than the length of the array list");
		
		if(index < 0)
			throw new ArrayIndexOutOfBoundsException("The specifed index cannot be less than 0");
	}

	public int add(String s) {
		ensureCapacity();
		int indexToAdd = arrayLength;
		arr[indexToAdd] = s;
		arrayLength++;
		return indexToAdd;
	}

	public String get(int i) {
		checkIndexIsInRange(i);
		return arr[i];
	}

	public boolean contains(String s) {
		return indexOf(s) >= 0;
	}

	public int indexOf(String s) {
		for (int i = 0; i < arrayLength; i++) {
			if (arr[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	public int size() {
		return arrayLength;
	}

	public int add(int index, String s) {
		checkIndexIsInRange(index);
		ensureCapacity();
		int currentIndex = arrayLength;
		while(currentIndex > index) {
			arr[currentIndex] = arr[currentIndex-1];
			currentIndex--;
		}
		arr[index] = s;
		arrayLength++;
		return index;
	}

	public void clear() {
		arr = new String[0];
		arrayLength = 0;
	}
	
	public boolean isEmpty() {
		return arrayLength == 0;
	}

	public String remove(int i) {
		checkIndexIsInRange(i);
		int currentIndex = i;
		String removed = arr[i];
		while(currentIndex < arr.length-1) {
			arr[currentIndex] = arr[currentIndex+1];
			currentIndex++;
		}
		arrayLength--;
		return removed;
	}

	public void set(int index, String s) {
		checkIndexIsInRange(index);
		arr[index] = s;
	}

	public String[] toArray() {
		String[] copy = new String[arrayLength];
		for(int i=0; i<arrayLength; i++) {
			// Makes this a copy of the array rather than a reference to the String
			copy[i] = arr[i] + ""; 
		}
		return copy;
	}
}
