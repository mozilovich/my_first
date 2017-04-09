package binarysearch;

/**
 * Created by Admin on 03.04.2017.
 */
public interface SearchTree<T> {
  void add(T value);
  boolean find(T value);
  void remove(T value);
}
