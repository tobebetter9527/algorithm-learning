package com.freedom.zuo.class36_ordered_table2;

/**
 * size balanced tree
 *
 * @author tobebetter9527
 * @create 2022/08/13 9:40
 */
public class Code01_SizeBalancedTreeMap {

  static class SBTNode<K extends Comparable<K>, V> {

    public K key;
    public V value;
    public SBTNode<K, V> l;
    public SBTNode<K, V> r;
    // 不同的key的数量
    public int size;

    public SBTNode(K key, V value) {
      this.key = key;
      this.value = value;
      size = 1;
    }
  }

  static class SizeBalancedTreeMap<K extends Comparable<K>, V> {

    private SBTNode<K, V> root;

    public int size() {
      return root != null ? root.size : 0;
    }

    public boolean containsKey(K key) {
      if (key == null) {
        return false;
      }
      SBTNode<K, V> lastNode = findLastIndex(key);
      return lastNode != null && key.compareTo(lastNode.key) == 0;
    }

    public void put(K key, V value) {
      if (key == null) {
        return;
      }

      SBTNode<K, V> last = findLastIndex(key);
      if (last != null && key.compareTo(last.key) == 0) {
        last.value = value;
      } else {
        root = add(root, key, value);
      }
    }

    public void remove(K key) {
      if (key == null) {
        return;
      }
      if (containsKey(key)) {
        root = delete(root, key);
      }
    }


    public K getIndexKey(int index) {
      if (index < 0 || index >= this.size()) {
        throw new RuntimeException("invalid parameter.");
      }
      return getIndex(root, index + 1).key;
    }

    public V getIndexValue(int index) {
      if (index < 0 || index >= this.size()) {
        throw new RuntimeException("invalid parameter.");
      }
      return getIndex(root, index + 1).value;
    }

    public V get(K key) {
      if (key == null) {
        return null;
      }
      SBTNode<K, V> lastNode = findLastIndex(key);
      if (lastNode != null && key.compareTo(lastNode.key) == 0) {
        return lastNode.value;
      } else {
        return null;
      }
    }

    public K firstKey() {
      if (root == null) {
        return null;
      }
      SBTNode<K, V> cur = root;
      while (cur.l != null) {
        cur = cur.l;
      }
      return cur.key;
    }

    public K lastKey() {
      if (root == null) {
        return null;
      }
      SBTNode<K, V> cur = root;
      while (cur.r != null) {
        cur = cur.r;
      }
      return cur.key;
    }

    public K floorKey(K key) {
      if (key == null) {
        throw new RuntimeException("invalid parameter.");
      }
      SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
      return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    public K ceilingKey(K key) {
      if (key == null) {
        throw new RuntimeException("invalid parameter.");
      }
      SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
      return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }


    /**
     * ???
     */
    private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
      if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
        return cur;
      } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
        return getIndex(cur.l, kth);
      } else {
        return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
      }
    }


    private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
      SBTNode<K, V> left = cur.l;
      cur.l = left.r;
      left.r = cur;
      // left变成头节点，所以其size等于cur的size
      left.size = cur.size;
      cur.size = getNodeSize(cur.l) + getNodeSize(cur.r) + 1;
      return left;
    }

    private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
      SBTNode<K, V> right = cur.r;
      cur.r = right.l;
      right.l = cur;

      // right变成头节点，所以其size等于cur的size
      right.size = cur.size;
      cur.size = getNodeSize(cur.l) + getNodeSize(cur.r) + 1;
      return right;
    }

    private SBTNode<K, V> reBalance(SBTNode<K, V> cur) {
      if (cur == null) {
        return null;
      }
      int leftSize = getNodeSize(cur.l);
      int llSize = getLLNodeSize(cur);
      int lrSize = getLRNodeSize(cur);

      int rightSize = getNodeSize(cur.r);
      int rrSize = getRRNodeSize(cur);
      int rlSize = getRLNodeSize(cur);

      // LL型
      if (llSize > rightSize) {
        cur = rightRotate(cur);
        // 子节点改变的节点需要再平衡,以下类似
        cur.r = reBalance(cur.r);
        cur = reBalance(cur);
      } else if (lrSize > rightSize) {
        cur.l = leftRotate(cur.l);
        cur = rightRotate(cur);

        cur.l = reBalance(cur.l);
        cur.r = reBalance(cur.r);
        cur = reBalance(cur);
      } else if (rrSize > leftSize) {
        cur = leftRotate(cur);

        cur.l = reBalance(cur.l);
        cur = reBalance(cur);
      } else if (rlSize > rightSize) {
        cur.r = rightRotate(cur.r);
        cur = leftRotate(cur);

        cur.l = reBalance(cur.l);
        cur.r = reBalance(cur.r);
        cur = reBalance(cur);
      }

      return cur;
    }

    private SBTNode<K, V> findLastIndex(K key) {
      SBTNode<K, V> pre = root;
      SBTNode<K, V> cur = root;
      while (cur != null) {
        pre = cur;
        if (key.compareTo(cur.key) == 0) {
          break;
        } else if (key.compareTo(cur.key) < 0) {
          cur = cur.l;
        } else {
          cur = cur.r;
        }
      }
      return pre;
    }

    /**
     * 找到>=当前key的最接近的节点
     */
    private SBTNode<K, V> findLastNoSmallIndex(K key) {
      SBTNode<K, V> pre = null;
      SBTNode<K, V> cur = root;
      while (cur != null) {
        if (key.compareTo(cur.key) == 0) {
          pre = cur;
          break;
        } else if (key.compareTo(cur.key) < 0) {
          pre = cur;
          cur = cur.l;
        } else {
          cur = cur.r;
        }
      }
      return pre;
    }

    /**
     * 找到<=当前key的最接近的节点
     */
    private SBTNode<K, V> findLastNoBigIndex(K key) {
      SBTNode<K, V> pre = null;
      SBTNode<K, V> cur = root;
      while (cur != null) {
        if (key.compareTo(cur.key) == 0) {
          pre = cur;
          break;
        } else if (key.compareTo(cur.key) < 0) {
          cur = cur.l;
        } else {
          pre = cur;
          cur = cur.r;
        }
      }
      return pre;
    }

    /**
     * 现在，以cur为头的树上，新增，加(key, value)这样的记录
     * <p>
     * 加完之后，会对cur做检查，该调整调整
     * <p>
     * 返回，调整完之后，整棵树的新头部
     */
    private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
      if (cur == null) {
        return new SBTNode<K, V>(key, value);
      }
      cur.size++;
      if (key.compareTo(cur.key) < 0) {
        cur.l = add(cur.l, key, value);
      } else {
        cur.r = add(cur.r, key, value);
      }
      return reBalance(cur);
    }

    /**
     * 在cur这棵树上，删掉key所代表的节点, 返回cur这棵树的新头部
     */
    private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
      cur.size--;
      if (key.compareTo(cur.key) < 0) {
        cur = delete(cur.l, key);
      } else if (key.compareTo(cur.key) > 0) {
        cur = delete(cur.r, key);
      } else {
        if (cur.l == null && cur.r == null) {
          cur = null;
        } else if (cur.l != null && cur.r == null) {
          cur = cur.l;
        } else if (cur.l == null && cur.r != null) {
          cur = cur.r;
        } else {
          // // 左右都有节点，可以用左子树的最右节点，或右子树的最左节点替代当前节点
          SBTNode<K, V> pre = null;
          SBTNode<K, V> des = cur.r;
          des.size--;
          while (des.l != null) {
            pre = des;
            des = des.l;
            des.size--;
          }

          if (pre != null) {
            pre.l = des.r;
            des.r = cur.r;
          }
          des.l = cur.l;
          des.size = cur.l.size + getNodeSize(cur.r) + 1;
          cur = des;
        }
      }
      // 可要也可不要
      // cur = reBalance(cur);
      return cur;
    }


    private int getNodeSize(SBTNode<K, V> cur) {
      return cur != null ? cur.size : 0;
    }

    private int getLLNodeSize(SBTNode<K, V> cur) {
      return cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
    }

    private int getLRNodeSize(SBTNode<K, V> cur) {
      return cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
    }

    private int getRRNodeSize(SBTNode<K, V> cur) {
      return cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
    }

    private int getRLNodeSize(SBTNode<K, V> cur) {
      return cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
    }
  }

 // ------------------------------------------------ //


  // for test
  public static void printAll(SBTNode<String, Integer> head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  // for test
  public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
    if (head == null) {
      return;
    }
    printInOrder(head.r, height + 1, "v", len);
    String val = to + "(" + head.key + "," + head.value + ")" + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    System.out.println(getSpace(height * len) + val);
    printInOrder(head.l, height + 1, "^", len);
  }

  // for test
  public static String getSpace(int num) {
    String space = " ";
    StringBuffer buf = new StringBuffer("");
    for (int i = 0; i < num; i++) {
      buf.append(space);
    }
    return buf.toString();
  }

  public static void main(String[] args) {
    SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
    sbt.put("d", 4);
    sbt.put("c", 3);
    sbt.put("a", 1);
    sbt.put("b", 2);
    // sbt.put("e", 5);
    sbt.put("g", 7);
    sbt.put("f", 6);
    sbt.put("h", 8);
    sbt.put("i", 9);
    sbt.put("a", 111);
    System.out.println(sbt.get("a"));
    sbt.put("a", 1);
    System.out.println(sbt.get("a"));
    for (int i = 0; i < sbt.size(); i++) {
      System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
    }
    printAll(sbt.root);
    System.out.println(sbt.firstKey());
    System.out.println(sbt.lastKey());
    System.out.println(sbt.floorKey("g"));
    System.out.println(sbt.ceilingKey("g"));
    System.out.println(sbt.floorKey("e"));
    System.out.println(sbt.ceilingKey("e"));
    System.out.println(sbt.floorKey(""));
    System.out.println(sbt.ceilingKey(""));
    System.out.println(sbt.floorKey("j"));
    System.out.println(sbt.ceilingKey("j"));
    sbt.remove("d");
    printAll(sbt.root);
    sbt.remove("f");
    printAll(sbt.root);

  }
}