package binarysearch;

/**
 * Created by Admin on 02.04.2017.
 */
public class Tree<T extends Comparable<T>> {
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
    return find(value, root).value.compareTo(value) == 0;
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
    if (root != null) {
      removing(value, root);
    }
  }

  private void removing(T value, Node<T> node) {
    Node<T> curr = find(value, node);

    if (curr.value.compareTo(value)==0) {
      if (curr.left == null) {
        if(curr.value.compareTo(parent.value)<0) {
          curr = parent.left.right;
          parent.left = curr;
        }
        else{
          curr = parent.right.right;
          parent.right = curr;
        }

      } else if (curr.right == null) {
        if(curr.value.compareTo(parent.value)<0) {
          curr = parent.left.left;
          parent.left = curr;
        }
        else {
          curr = parent.right.left;
          parent.right = curr;
        }

      } else {
        if (curr.value.compareTo(parent.value) >= 0) {
          curr = parent.right.right;
          Node<T> result = curr;
          Node<T> parent2 = parent;
          while(result.left!=null) {
            parent2 = result;
            result = result.left;
          }
          parent2.left = result.right;
          result.right = parent.right.right;
          result.left = parent.right.left;
          parent.right = result;

        } else {
          curr = parent.left.left;
          Node<T> result = curr;
          Node<T> parent2 = parent;
          while(result.right!=null) {
            parent2 = result;
            result = result.right;
          }
          parent2.right = result.left;
          result.left = parent.left.left;
          result.right = parent.left.right;
          parent.left = result;

        }
      }
    }
  }
public void zig(Node<T> y ,Node<T>  x ){
  y.left = x.right;
  x.right = y;
  root = x;
}

}
