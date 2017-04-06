package binarysearch;

/**
 * Created by Admin on 02.04.2017.
 */
public class Tree<T extends Comparable<T>>  {
  private Node<T> root;

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
        node.right = new Node(null, null, value);
    } else {
      if (node.left != null)
        adding(value, node.left);
      else
        node.left = new Node(null, null, value);
    }
  }

  public boolean contains(T value) {
    if (root == null) {
      return false;
    }
    return find(value, root);
  }

  private boolean find(T value, Node<T> node) {
    if (value.compareTo(node.value) > 0 && node.right != null) {
      return find(value, node.right);
    }
    if (value.compareTo(node.value) < 0 && node.left != null) {
      return find(value, node.left);
    }
    return value.compareTo(node.value) == 0;
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
      node = node.left;//если удаляем левый , то заменяем на левый
      node.left = null;
      node = node.right;// если удаляем правый , то заменяем на правый
      node.right = null;

    }
  }

}