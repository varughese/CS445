public class LinkedQueue<T> implements Queue<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public LinkedQueue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public T remove() {
		if(isEmpty()) throw new UnsupportedOperationException("Empty Queue");
		T result = peek();
		head = head.getNext();
		size--;
		if(isEmpty()) tail = null;
		return result;
	}

	@Override
	public T peek() {
		if(isEmpty()) throw new UnsupportedOperationException("Empty Queue");
		return head.getValue();
	}

	@Override
	public void add(T thing) {
		if(isEmpty()) {
			head = tail = new Node<T>(thing);
		} else {
			Node<T> n = new Node<T>(thing);
			tail.setNext(n);
			tail = n;
		}
		size++;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	@SuppressWarnings({ "unused", "hiding" })
	private class Node<T> {
		private Node<T> next;
		T value;
		
		public Node(T value) {
			this(value, null);
		}
		
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
		
		public T getValue() {
			return value;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public Node<T> getNext() {
			return next;
		}		
	}
}
