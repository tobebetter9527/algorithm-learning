package com.freedom.zuo.class35_ordered_table1;


/**
 * An AVL tree is a balanced binary search tree, that is, a binary search tree in which the heights of left and right
 * <p>
 * subtrees of each node differ by at most one.
 *
 * @author tobebetter9527
 * @create 2022/08/11 21:35
 */
public class Code01_AVLTreeMap {

  static class AVLNode<K extends Comparable<K>, V> {

    public K k;
    public V v;
    public AVLNode<K, V> left;
    public AVLNode<K, V> right;
    // height
    public int h;

    public AVLNode(K key, V value) {
      k = key;
      v = value;
      h = 1;
    }
  }

  // ----------------------------------------------------------------------------------------------------- //

  static class AVLTreeMap<K extends Comparable<K>, V> {

    private AVLNode<K, V> root;
    private int size;

    public AVLTreeMap() {
      root = null;
      size = 0;
    }

    /**
     * 右旋
     */
    private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
      AVLNode<K, V> left = cur.left;
      cur.left = left.right;
      left.right = cur;
      // update the cur node first,then the left node.
      cur.h = Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;
      left.h = Math.max(getHeight(left.left), getHeight(left.right)) + 1;
      return left;
    }

    /**
     * 左旋
     */
    private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
      AVLNode<K, V> right = cur.right;
      cur.right = right.left;
      right.left = cur;

      cur.h = Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;
      right.h = Math.max(getHeight(right.left), getHeight(right.right)) + 1;
      return right;
    }

    private int getHeight(AVLNode<K, V> cur) {
      return cur != null ? cur.h : 0;
    }


  }

}
