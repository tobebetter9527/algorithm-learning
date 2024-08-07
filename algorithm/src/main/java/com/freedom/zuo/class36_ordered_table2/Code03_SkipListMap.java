package com.freedom.zuo.class36_ordered_table2;

import java.util.ArrayList;
import java.util.List;

/**
 * skip list
 *
 * @author tobebetter9527
 * @create 2022/08/13 17:01
 */
public class Code03_SkipListMap {

    // --------------------------------------------------------------- //
    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
    }

    static class SkipListNode<K extends Comparable<K>, V> {

        public K key;
        public V val;
        // 也可以用数组实现
        public List<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<>();
        }

        public SkipListNode(K k, V v, int level) {
            key = k;
            val = v;
            nextNodes = new ArrayList<>(level + 1);
            for (int i = 0; i <= level; i++) {
                nextNodes.add(null);
            }
        }


        // 遍历的时候，如果是往右遍历到的null(next == null), 遍历结束
        // 头(null), 头节点的null，认为最小
        // node  -> 头，node(null, "")  node.isKeyLess(!null)  true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            //  otherKey == null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    static class SkipListMap<K extends Comparable<K>, V> {

        // < 0.5 继续做，>=0.5 停
        private static final double PROBABILITY = 0.5;
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null); // 0
            size = 0;
            maxLevel = 0;
        }


        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }

            SkipListNode<K, V> lessNode = mostRightLessNodeInTree(key);
            SkipListNode<K, V> findNode = lessNode.nextNodes.get(0);

            if (findNode != null && findNode.isKeyEqual(key)) {
                findNode.val = value;
            } else {
                size++;

                int newLevel = randomLevel();
                if (newLevel > maxLevel) {
                    adjustHead(newLevel);
                }

                SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, value, newLevel);
                SkipListNode<K, V> cur = head;
                for (int level = maxLevel; level >= 0; level--) {
                    cur = mostRightLessNodeInLevel(cur, key, level);
                    if (level <= newLevel) {
                        newNode.nextNodes.set(level, cur.nextNodes.get(level));
                        cur.nextNodes.set(level, newNode);
                    }
                }
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }

        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                SkipListNode<K, V> cur = head;
                for (int level = maxLevel; level >= 0; level--) {
                    cur = mostRightLessNodeInLevel(cur, key, level);
                    SkipListNode<K, V> next = cur.nextNodes.get(level);

                    if (next != null && next.isKeyEqual(key)) {
                        cur.nextNodes.set(level, next.nextNodes.get(level));
                    }

                    if (level != 0 && cur == head && cur.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                }
            }
        }

        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }


        private void adjustHead(int newLevel) {
            while (newLevel > maxLevel) {
                head.nextNodes.add(null);
                maxLevel++;
            }
        }

        /**
         * 这个是关键，决定新节点的高度
         */
        private int randomLevel() {
            int newNodeLevel = 0;
            while (Math.random() < PROBABILITY) {
                newNodeLevel++;
            }
            return newNodeLevel;
        }

        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }

            SkipListNode<K, V> cur = head;
            for (int level = maxLevel; level >= 0; level--) {
                cur = mostRightLessNodeInLevel(cur, key, level);
            }
            return cur;
        }

        private SkipListNode<K, V> mostRightLessNodeInLevel(SkipListNode<K, V> cur, K key, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }
    }
}
