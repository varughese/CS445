public class LinkedStack<T> implements Stack<T> {
	@SuppressWarnings({ "unused", "hiding" })
	private class Node<T> {
		private T value;
		private Node<T> next;
		
		public Node() {}
		
		public Node(T _value, Node<T> _next) {
			value = _value;
			next = _next;
		}
		
		public Node(T _value) {
			this(_value, null);
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		public T getValue() {
			return value;
		}
		
		public void setNext(Node<T> n) {
			next = n;
		}
	}
	
	private Node<T> head;
	private int size;
	
	private void checkEmptiness() {
		if(isEmpty()) throw new UnsupportedOperationException("Stack is empty"); 
	}
	
	public LinkedStack() {
		head = null;
		size = 0;
	}

	public T pop() {
		T result = peek();
		head = head.getNext();
		size--;
		return result;
	}

	public T peek() {
		checkEmptiness();
		return head.getValue();
	}

	public void push(T thing) {
		if(isEmpty()) {
			head = new Node<T>(thing);
		} else {
			head = new Node<T>(thing, head);
		}
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
