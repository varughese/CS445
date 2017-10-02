import static org.junit.Assert.*;
import org.junit.Test;

public class StringLinkedListTest {

  @Test
  public void testAdd() {
    // Test basic creation.
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    list.add(s);
    assertEquals(list.size(), 1);
    assertEquals(list.get(0), s);
    
    // Test growth / capacity.
    list = new StringLinkedList();
    int size = 100000;
    for (int i = 0; i < size; i++) {
      list.add(Integer.toString(i));
    }
    assertEquals(size, list.size());
  }

  @Test
  public void testGet() {
    // test basic get
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    list.add(s);
    assertEquals(list.get(0), s);
    
    // test exception if index is out of bounds
    try {
      list.get(10);
      fail();
    } catch (Exception exc) {
      // OK - this should have thrown an exception.
    }
  }

  @Test
  public void testContains() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String s2 = "Test 2";
    list.add(s2);
    list.add(s);
    list.add(s2);
    assertTrue(list.contains(s));
    assertFalse(list.contains("Nonexistent"));
  }

  @Test
  public void testIndexOf() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    String u = "Test 3";
    list.add(s);
    list.add(t);
    list.add(u);
    assertEquals(list.indexOf(s), 0);
    assertEquals(list.indexOf(t), 1);
    assertEquals(list.indexOf(u), 2);
    list.remove(1);
    assertEquals(list.indexOf(u), 1);
    list.remove(0);
    assertEquals(list.indexOf(u), 0);
    
    // test negative case
    assertEquals(list.indexOf("Not here"), -1);
  }

  @Test
  public void testSize() {
    StringLinkedList list = new StringLinkedList();
    assertEquals(list.size(), 0);
    String s = "Test 1";
    list.add(s);
    assertEquals(list.size(), 1);
    
    list.clear();
    assertEquals(list.size(), 0);
  }

  @Test
  public void testInsert() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    list.add(s);
    String t = "Test 2";
    list.add(0, t);
    assertEquals(list.get(0), t);
    assertEquals(list.get(1), s);
    
    // test out of bounds
    try {
      list.add(100, s);
      fail();
    } catch (Exception exc) {
      // OK - this should have thrown an exception.
    }
    
    // Test proper growth for insert
    list = new StringLinkedList();
    int size = 10000;
    list.add("0");
    for (int i = 1; i < size; i++) {
      list.add(0, Integer.toString(i));
    }
    assertEquals(list.get(size - 1), "0");
    
    list.clear();
    list.add(list.size(), s);
    list.add(list.size(), t);
    list.add(list.size(), s);
    list.add(0, t);
    assertEquals(list.get(0), t);
    assertEquals(list.get(1), s);
  }

  @Test
  public void testClear() {
    StringLinkedList list = new StringLinkedList();
    list.add("x");
    list.clear();
    assertTrue(list.isEmpty());
    assertEquals(list.size(), 0);
  }

  @Test
  public void testIsEmpty() {
    StringLinkedList list = new StringLinkedList();
    assertTrue(list.isEmpty());
    list.add("x");
    assertFalse(list.isEmpty());
    list.clear();
    assertTrue(list.isEmpty());
  }

  @Test
  public void testRemove() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    list.add(s);
    list.add(t);
    list.remove(0);
    assertEquals(list.size(),1);
    assertEquals(list.get(0), t);
    list.remove(0);
    assertTrue(list.isEmpty());
    
    // test bounds
    try { 
      list.remove(0);
      fail();
    } catch (Exception exc) {
      // OK - this should have thrown an exception.
    }
    
    list.add(s);
    list.add(t);
    list.remove(1);
    assertEquals(list.get(0), s);
    list.add(t);
    assertEquals(list.get(1), t);
    list.add(s);
  }

  @Test
  public void testSet() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    String u = "New";
    list.add(s);
    list.add(t);
    list.set(0, u);
    assertEquals(list.get(0), u);
    assertEquals(list.get(1), t);

    // test bounds
    try { 
      list.set(10000, u);
      fail();
    } catch (Exception exc) {
      // OK - this should have thrown an exception.
    }
  }
  
  @Test
  public void testRemoveAll() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    list.add(s);
    list.add(t);
    list.add(s);
    list.add(t);
    list.add(s);
    list.add(t);
    list.remove(s);
    assertEquals(list.size(), 3);
    assertEquals(list.get(0), t);
    assertEquals(list.get(1), t);
    assertEquals(list.get(2), t);
  }

  @Test
  public void testToArray() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    list.add(s);
    list.add(t);
    String[] strings = list.toArray();
    assertEquals(strings[0], s);
    assertEquals(strings[1], t);
    
    assertEquals(strings.length, 2);
    
    // Test to ensure that changing strings[] does not change the list.
    strings[0] = "Something else";
    assertEquals(list.get(0), s);
  }
  
  @Test
  public void iterate() {
    StringLinkedList list = new StringLinkedList();
    String s = "Test 1";
    String t = "Test 2";
    list.add(s);
    list.add(t);
    list.add("Yo");
    list.add("Yod");
    list.add("Yof");
    for(String sss : list) {
    		System.out.println(sss);
    }
  }
}