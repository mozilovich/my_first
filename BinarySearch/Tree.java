package binarysearch;

/**
 * Created by Admin on 02.04.2017.
 */
public class Tree<T extends Comparable<T>> {
  private Node<T> root;

  public void add(T value) {
    if (root == null)
      root = new Node<T>(null, null, value, null);
    else
      adding(value, root);
  }

  private void adding(T value, Node<T> node) {
    if (value.compareTo(node.value) >= 0) {
      if (node.right != null)
        adding(value, node.right);
      else
        node.right = new Node<T>(null, null, value, node);
    } else {
      if (node.left != null)
        adding(value, node.left);
      else
        node.left = new Node<T>(null, null, value, node);
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
      return find(value, node.right);
    }
    if (value.compareTo(node.value) < 0 && node.left != null) {
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
    Node<T> temp = curr.parent;

    if (curr.value.compareTo(value) == 0) {
      if (curr.left == null) {
        if (curr.value.compareTo(curr.parent.value) < 0) {
          curr = curr.right;
          curr.parent = temp;
          curr.parent.left = curr;
        } else {
          curr = curr.right;
          curr.parent = temp;
          curr.parent.right = curr;
        }

      } else if (curr.right == null) {
        if (curr.value.compareTo(curr.parent.value) < 0) {
          curr = curr.left;
          curr.parent = temp;
          curr.parent.left = curr;
        } else {
          curr = curr.left;
          curr.parent = temp;
          curr.parent.right = curr;
        }

      } else {
        if (curr.value.compareTo(curr.parent.value) >= 0) {
          Node<T> result = curr.right;

          while (result.left != null) {
            Node<T> currParent = result;
            result = result.left;
            result.parent = currParent;
          }

          result.parent.left = result.right;
          result.right.parent = result.parent;
          result.right = curr.right;
          result.right.parent = result;
          result.left = curr.left;
          result.left.parent = result;
          curr.parent.right = result;
          result.parent = curr.parent;


        } else {
          Node<T> result = curr.right;
          while (result.left != null) {
            Node<T> currParent = result;
            result = result.left;
            result.parent = currParent;
          }
          result.parent.left = result.right;
          result.right.parent = result.parent;
          result.left = curr.left;
          result.left.parent = result;
          result.right = curr.right;
          result.right.parent = result;
          curr.parent.left = result;
          result.parent = curr.parent;

        }
      }
    }
  }

  public void zig(Node<T> x) {
    if (x.value.compareTo(root.value) < 0) {
      root.right = x.left;
      x.left = root;
      root = x;
      x.left.parent = x;
      x.parent = null;
      x.left.right.parent = x.left;
    } else {
      root.left = x.right;
      x.right = root;
      root = x;
      x.right.parent = x;
      x.parent = null;
      x.right.left.parent = x.right;
    }
  }

  public void zigZig(Node<T> x) {
    Node<T> z = x.parent.parent;

    Node<T> parent1 = z.parent;
    Node<T> x1 = x;
    Node<T> y1 = x.parent;
    Node<T> z1 = z;

    if (z.value.compareTo(x.value) < 0) {

      x.left = z.right;
      z.right.right = x1.left;
      z.right.left = z;
      z.right = y1.left;
      z.right.parent = z;
      z.parent = x.left;
      x.left.right.parent = x.left;
      x.left.parent = x;

      if (z1.parent == null) {
        root = x;
      } else {
        x.parent = parent1;
        if (isLeft(z1)) {
          parent1.left = x;
        } else {
          parent1.right = x;
        }
      }


    } else {

      x.right = z.left;
      z.left.left = x1.right;
      z.left.right = z;
      z.left = y1.right;
      z.left.parent = z;
      z.parent = x.right;
      x.right.left.parent = x.right;
      x.right.parent = x;

      if (z1.parent == null) {
        root = x;
      } else {
        x.parent = z1.parent;
        if (isLeft(z1)) {
          parent1.left = x;
        } else {
          parent1.right = x;
        }
      }

    }
  }

  public void zigZag(Node<T> z, Node<T> x) {
    Node<T> parent1 = z.parent;
    Node<T> x1 = x;
    Node<T> y1 = x.parent;
    Node<T> z1 = z;

    if (z.value.compareTo(x.value) < 0) {
      z.right.left = x1.right;
      x.left = z;
      x.right = z.right;
      z.right = x1.left;
      z.right.parent = z;
      x.right.left.parent = x.right;
      z.parent = x;
      z.right.parent = x;

      if (z1.parent == null) {
        root = x;
      } else {
        x.parent = z1.parent;
        if (isLeft(z1)) {
          parent1.left = x;
        } else {
          parent1.right = x;
        }
      }

    } else {
      z.left.right = x1.left;
      x.right = z;
      x.left = z.left;
      z.left = x1.right;
      z.left.parent = z;
      x.left.right.parent = x.left;
      z.parent = x;
      z.left.parent = x;

      if (z1.parent == null) {
        root = x;
      } else {
        x.parent = z1.parent;
        if (isLeft(z1)) {
          parent1.left = x;
        } else {
          parent1.right = x;
        }
      }
    }
  }

  private boolean isLeft(Node<T> node) {
    if (node == node.parent.left) {
      return true;
    } else
      return false;
  }

}



