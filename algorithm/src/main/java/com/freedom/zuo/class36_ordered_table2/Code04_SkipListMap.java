package com.freedom.zuo.class36_ordered_table2;

import java.util.Random;

/**
 * skip list
 * <p>
 * from: https://opendsa-server.cs.vt.edu/OpenDSA/Books/CS3/html/SkipList.html
 *
 * @author tobebetter9527
 * @create 2022/08/14 9:51
 */
public class Code04_SkipListMap {

  public static void main(String[] args) {
    SkipList<Integer, Integer> skipList = new SkipList<>();
    skipList.insert(1, 1);
    skipList.insert(10, 10);
    skipList.insert(5, 5);
    skipList.insert(6, 6);
    skipList.insert(3, 3);
    skipList.insert(2, 2);
    skipList.insert(8, 8);
    System.out.println(skipList.head);
    Integer integer = skipList.find(8);
    System.out.println(integer);
  }


  static class SkipList<K extends Comparable<K>, E> {

    private SkipNode<K, E> head;
    private int level;
    private int size;
    static private Random ran = new Random(); // Hold the Random class object

    public SkipList() {
      head = new SkipNode<K, E>(null, null, 0);
      level = -1;
      size = 0;
    }


    // Return the (first) matching matching element if one exists, null otherwise
    public E find(K key) {
      SkipNode<K, E> x = head; // Dummy header node
      for (int i = level; i >= 0; i--) {// For each level...
        while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0)) { // go forward
          x = x.forward[i]; // Go one last step
        }
      }

      x = x.forward[0]; // Move to actual record, if it exists
      if ((x != null) && (x.key().compareTo(key) == 0)) {
        return x.element(); // Got it
      } else {
        return null; // Its not there
      }
    }

    /**
     * Insert a key, element pair into the skip list
     */
    public void insert(K key, E elem) {
      int newLevel = randomLevel(); // New node's level
      if (newLevel > level) { // If new node is deeper
        adjustHead(newLevel); // adjust the header
      }

      // Track end of level
      SkipNode<K, E>[] update = new SkipNode[level + 1];
      SkipNode<K, E> x = head; // Start at header node
      for (int i = level; i >= 0; i--) { // Find insert position
        while ((x.forward[i] != null) && (x.forward[i].key().compareTo(key) < 0)) {
          x = x.forward[i];
        }
        update[i] = x; // Track end at level i
      }

      x = new SkipNode<K, E>(key, elem, newLevel);
      for (int i = 0; i <= newLevel; i++) { // Splice into list
        x.forward[i] = update[i].forward[i]; // Who x points to
        update[i].forward[i] = x; // Who points to x
      }
      size++; // Increment dictionary size
    }

    private void adjustHead(int newLevel) {
      SkipNode<K, E> temp = head;
      head = new SkipNode<K, E>(null, null, newLevel);
      for (int i = 0; i <= level; i++) {
        head.forward[i] = temp.forward[i];
      }
      level = newLevel;
    }

    // Pick a level using a geometric distribution
    int randomLevel() {
      int lev;
      for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) { // ran is random generator
        ; // Do nothing
      }
      return lev;
    }

  }


  static class SkipNode<K extends Comparable<K>, E> {

    private KVPair<K, E> rec;
    private SkipNode<K, E>[] forward;

    public E element() {
      return rec.value();
    }

    public K key() {
      return rec.key();
    }

    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
      rec = new KVPair<K, E>(key, elem);
      forward = new SkipNode[level + 1];
      for (int i = 0; i < level; i++) {
        forward[i] = null;
      }
    }

    @Override
    public String toString() {
      return rec.toString();
    }

    private class KVPair<K, E> {

      public K key;
      public E value;

      public KVPair(K key, E value) {
        this.key = key;
        this.value = value;
      }

      public K key() {
        return key;
      }

      public E value() {
        return value;
      }
    }
  }
}
