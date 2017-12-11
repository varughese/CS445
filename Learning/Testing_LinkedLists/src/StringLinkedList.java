import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Iterator;

public class StringLinkedList implements Iterable<String> {

  private Node head;
  private Node tail;
  private int size = 0;
  
  public int add(String s) {
    if (head == null) {
      head = tail = new Node(s);
    } else {
      tail.setNext(new Node(s));
      tail = tail.getNext();
    }
    return size++;
  }
  
  public Iterator<String> iterator() {
	  return new LinkedListIterator<String>();
  }
   
  public int add(int i, String s) {
    if (i == size) {
      add(s);
    } else {
      checkBounds(i);
      if (i == 0) {
        head = new Node(s, head);
      } else {
        Node n = getNodeAt(i - 1);
        n.setNext(new Node(s, n.getNext()));
      }
    }
    size++;
    return i;
  }

  public boolean isEmpty() {
    return (head == null);
  }
  
  public int size() {
    return size;
  }
  
  public void clear() {
    head = tail = null;
    size = 0;
  }
  
  public String remove(String s) {
    while (!isEmpty() && head.equalsValue(s)) {
      head = head.getNext();
      size--;
    }
    if (isEmpty()) {
      tail = null;
    } else {
      Node current = head;
      while (current.hasNext()) {
        if (current.getNext().equalsValue(s)) {
          current.setNext(current.getNext().getNext());
          size--;
        } else {
          current = current.getNext();
        }
      }
      if (!current.hasNext()) {
        tail = current;
      }
    }
    return s;
  }
  
  public String remove(int i) {
    checkBounds(i);
    String result;
    if (i == 0) {
      result = head.getValue();
      head = head.getNext();
    } else {
      Node n = getNodeAt(i - 1);
      result = n.getNext().getValue();
      n.setNext(n.getNext().getNext());
      if (!n.hasNext()) {
        tail = n;
      }
    }
    size--;
    return result;
  }
  
  public String get(int i) {
    checkBounds(i);
    return getNodeAt(i).value;
  }
  
  public void set(int i, String s) {
    checkBounds(i);
    Node n = getNodeAt(i);
    n.setValue(s);
  }
  
  public int indexOf(String s) {
    int result = 0;
    for (Node current = head; current != null; current = current.getNext()) {
      if (current.equalsValue(s)) {
        return result;
      }
      result++;
    }
    return -1;
  }
  
  public boolean contains(String s) {
    return indexOf(s) >= 0;
  }
  
  public String[] toArray() {
    String[] result = new String[size];
    int i = 0;
    for (Node current = head; current != null; current = current.getNext()) {
      result[i++] = current.getValue();
    }
    return result;
  }   
  
  private void checkBounds(int i) {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException("Index is outside of list bounds.");
    }
  }
    
  private Node getNodeAt(int i) {
    int count = 0;
    for (Node current = head; current != null; current = current.getNext()) {
      if (count == i) {
        return current;
      }
      count++;
    }
    return null;
  }

  private class LinkedListIterator<String> implements Iterator<String> {
	  private Node current;
	  
	  public LinkedListIterator() {
		  current = head;
	  }
	  
	  public boolean hasNext() {
		  return current != null;
	  }
	  
	  public String next() {
		  if(hasNext()) {
			  String value = (String) current.getValue();
			  current = current.getNext();
			  return value;
		  } else {
			  throw new UnsupportedOperationException("No more next");
		  }
	  }
  }
  
  private class Node {
    private String value;
    private Node next;
    
    public Node(String value, Node next) {
      this.value = value;
      this.next = next;
    }
    
    public Node(String value) {
      this(value, null);
    }
    
    public boolean hasNext() {
      return next != null;
    }
    
    public void setNext(Node n) {
      next = n;
    }
    
    public Node getNext() {
      return next;
    }
    
    public String getValue() {
      return value;
    }
    
    public void setValue(String s) {
      value = s;
    }
    
    public boolean equalsValue(String value) {
      if (this.value == null) {
        return value == null;
      } else {
        return this.value.equals(value);
      }
    } 
  }
}
