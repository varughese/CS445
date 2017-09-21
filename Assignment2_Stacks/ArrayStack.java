public class ArrayStack<T> implements Stack<T>{
	private T[] arr;
	private int size;
	
	public ArrayStack() {
		arr = (T[])new Object[10];
		size = 0;
	}
	
	private void ensureCapacity() {
		if(size == arr.length) {
			T[] temp = (T[])new Object[arr.length * 2 + 1];
			System.arraycopy(arr, 0, temp, 0, arr.length);
			arr = temp;		
		}
	}

	private void checkEmptiness() {
		if(isEmpty()) throw new UnsupportedOperationException("Stack is empty"); 
	}
	
	public T pop() {
		checkEmptiness();
		return arr[--size];
	}

	public T peek() {
		checkEmptiness();
		return arr[size-1];
	}

	public void push(T thing) {
		ensureCapacity();
		arr[size] = thing;
		size++;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
