package binarysearch;

/**
 * Created by Admin on 02.04.2017.
 */
public class Tree<T extends Comparable<T>>  {
  private Node<T> root;
  private Node<T> parent;

  public void add(T value) {
    if (root == null)
      root = new Node<T>(null, null, value);
    else
      adding(value, root);
  }

  private void adding(T value, Node<T> node) {
    if (value.compareTo(node.value) >= 0) {
      if (node.right != null)
        adding(value, node.right);
      else
        node.right = new Node<T>(null, null, value);
    } else {
      if (node.left != null)
        adding(value, node.left);
      else
        node.left = new Node<T>(null, null, value);
    }
  }

  public boolean contains(T value) {
    if (root == null) {
      return false;
    }
    return find(value, root).value.compareTo(value)==0;
  }

  private Node<T> find(T value, Node<T> node) {
    if (value.compareTo(node.value) > 0 && node.right != null) {
      parent = node;
      return find(value, node.right);
    }
    if (value.compareTo(node.value) < 0 && node.left != null) {
      parent = node;
      return find(value, node.left);
    }
    return node;
  }

  public void remove(T value) {
    if(root != null) {
      removing(value, root);
    }
  }

  private void removing(T value, Node<T> node) {
    if(contains(value)){
      while(value.compareTo(node.value)!=0){
        if (value.compareTo(node.value) > 0 && node.right != null) {
          find(value, node.right);
        }
        else {
          find(value, node.left);
        }
      }
      if(node.left==null && node.right!=null){
        node=node.right;
      }
      else if(node.left!=null && node.right==null){
        node=node.left;
      }
      else{
        if(node.value.compareTo(parent.value)>=0) {
          node = node.right;
          node.right = null;
        }
        else {
          node = node.left;
          node.left = null;
        }
      }
    }
  }


}
