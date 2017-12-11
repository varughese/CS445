/*
 * For this assignment, you will implement an Itereator that operates as a depth-first-search of a binary tree.  A TreeSet is provided for you here.

25 points: code compiles and runs properly
20 points: proper implementation of StringIterator in StringTreeSet (i.e., values are returned in order)
5 points: grader's discretion
 */

import java.util.Stack;

public class StringTreeSet {

  private int size;
  private TreeNode root;
  
  public StringIterator iterator() {
    return new TreeIterator();
  }
  
  public int size() {
    return size;
  }
  
  public void add(String value) {
    if (root == null) {
      root = new TreeNode(value);
      size++;
    } else {
      add(value, root);
    }
  }
  
  public boolean contains(String value) {
    return contains(value, root);
  }
  
  public String remove(String value) {
    if (size > 0) {
      int c = value.compareTo(root.value);
      if (c == 0) {
        return removeRoot();
      } else {
        TreeNode parent = getParentOf(value);
        if (parent != null) {
          c = value.compareTo(parent.value);
          TreeNode remove = null;
          if (c < 0) {
            remove = parent.left;
            size--;
            if (remove.countChildren() == 2) {
              return complexRemove(remove);
            } else {
              if (remove.countChildren() == 0 || remove.hasLeftChild()) {
                parent.left = remove.left;
              } else {
                parent.left = remove.right;
              }
              return remove.value;
            }
          } else {
            remove = parent.right;
            size--;
            if (remove.countChildren() == 2) {
              return complexRemove(remove);
            } else {
              if (remove.countChildren() == 0 || remove.hasLeftChild()) {
                parent.right = remove.left;
              } else {
                parent.right = remove.right;
              }
              return remove.value;
            }
          }
        }
      }
    }
    return null;
  }
  
  private class TreeIterator implements StringIterator {
    Stack<TreeNode> stack;
    private void addToStack(Stack<TreeNode> s, TreeNode current) {
    		s.add(current);
    		if(current.hasLeftChild()) {
    			addToStack(s, current.left);
    		}
    }
    
	public TreeIterator() {
    		stack = new Stack<TreeNode>();
    		addToStack(stack, root);
    }
    
    public String next() {
    		if(!hasNext()) throw new IndexOutOfBoundsException("Empty");
    		
    		TreeNode treenode = stack.pop();
    		if(treenode.hasRightChild()) {
    			addToStack(stack, treenode.right);
    		}
    		return treenode.value;
    }
    
    public boolean hasNext() {
      return !stack.isEmpty();
    }
  }
  
  private String complexRemove(TreeNode node) {
    String result = node.value;
    if (goLeft()) {
      if (node.left.hasRightChild()) {
        TreeNode parent = getParentOfRightmostNode(node.left);
        node.value = parent.right.value;
        parent.right = parent.right.left;
      } else {
        node.value = node.left.value;
        node.left = node.left.left;
      }
    } else {
      if (node.right.hasLeftChild()) {
        TreeNode parent = getParentOfLeftmostNode(node.right);
        node.value = parent.left.value;
        parent.left = parent.left.right;
      } else {
        node.value = node.right.value;
        node.right = node.right.right;
      }
    }
    return result;
  }
  
  private TreeNode getParentOfLeftmostNode(TreeNode node) {
    TreeNode current = node;
    while (current.left.hasLeftChild()) {
      current = current.left;
    }
    return current;
  }
  
  private TreeNode getParentOfRightmostNode(TreeNode node) {
    TreeNode current = node;
    while (current.right.hasRightChild()) {
      current = current.right;
    }
    return current;
  }
  
  private boolean goLeft() {
    return Math.random() < 0.5;
  }
  
  private TreeNode getParentOf(String value) {
    TreeNode parent = root;
    TreeNode current;
    int c = value.compareTo(parent.value);
    if (c < 0) {
      current = parent.left;
    } else {
      current = parent.right;
    }
    while (current != null) {
      c = value.compareTo(current.value);
      if (c == 0) {
        return parent;
      } else if (c < 0) {
        parent = current;
        current = current.left;
      } else {
        parent = current;
        current = current.right;
      }
    }
    return null;
  }
  
  private String removeRoot() {
    String result = root.value;
    if (root.countChildren() == 0) {
      root = null;
    } else if (root.countChildren() == 2) {
      complexRemove(root);
    } else if (root.hasLeftChild()) {
      root = root.left;
    } else {
      root = root.right;
    }
    size--;
    return result;
  }
  
  private boolean contains(String value, TreeNode node) {
    if (node != null) {
      int c = value.compareTo(node.value);
      if (c == 0) {
        return true;
      } else if (c < 0) {
        return contains(value, node.left);
      } else {
        return contains(value, node.right);
      }
    }
    return false;
  }

  private void add(String value, TreeNode node) {
    int c = value.compareTo(node.value);
    if (c == 0) {
      node.value = value;
    } else if (c < 0) {
      if (node.hasLeftChild()) {
        add(value, node.left);
      } else {
        node.left = addTreeNode(value);
      }
    } else {
      if (node.hasRightChild()) {
        add(value, node.right);
      } else {
        node.right = addTreeNode(value);
      }
    }
  }  
  
  private TreeNode addTreeNode(String value) {
    size++;
    return new TreeNode(value);
  }
  
  private class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private String value;
    
    public TreeNode(String value) {
      this.value = value;
    }
    
    public boolean hasLeftChild() {
      return left != null;
    }
    
    public boolean hasRightChild() {
      return right != null;
    }
    
    public int countChildren() {
      int result = 0;
      if (hasLeftChild()) {
        result++;
      }
      if (hasRightChild()) {
        result++;
      } 
      return result;
    }
  }
}