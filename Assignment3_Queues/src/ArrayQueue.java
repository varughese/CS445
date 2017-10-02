public class ArrayQueue<T> implements Queue<T> {
	private Object[] array;
	private int size, first, last;

	
	public ArrayQueue() {
		array = new Object[10];
		size = first = last = 0;
	}
	
	@Override
	public T remove() {
		T result = peek();
		first++;
		if(first >= array.length) first = 0;
		size--;
		return result;
	}

	@Override
	public T peek() {
		if(isEmpty()) throw new UnsupportedOperationException("Empty Queue");
		return (T) array[first];
	}

	@Override
	public void add(T thing) {
		ensureCapacity();
		array[last] = thing;
		last++;
		if(last >= array.length) last = 0;
		size++;
	}
	
	private void ensureCapacity() {
		if(first == last && !isEmpty()) {	
			Object temp[] = new Object[array.length * 2 + 1];
			for(int i=0; i<size-first; i++) {
				temp[i] = array[i+first];
			}
			for(int i=0; i<last; i++) {
				temp[i+(size-first)] = array[i];
			}
			array = temp;
			first = 0;
			last = size;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
