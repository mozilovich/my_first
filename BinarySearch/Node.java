package binarysearch;

/**
 * Created by Admin on 02.04.2017.
 */
public class Node<T extends Comparable<T>> {
    Node left;
    Node right;
    T value;
  Node(Node left , Node right , T value){
    this.left = left;
    this.right = right;
    this.value = value;
  }

}
