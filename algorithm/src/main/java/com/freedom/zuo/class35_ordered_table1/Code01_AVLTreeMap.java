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

        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }

            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex != null && key.compareTo(lastIndex.k) == 0 ? lastIndex.v : null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.k;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.k;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }


        /**
         * 在cur这棵树上，删掉key所代表的节点
         * <p>
         * 返回cur这棵树的新头部
         */
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.k) < 0) {
                cur.left = delete(cur.left, key);
            } else if (key.compareTo(cur.k) > 0) {
                cur.right = delete(cur.right, key);
            } else {
                // 叶子节点
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else {
                    // 左右都有节点，可以用左子树的最右节点，或右子树的最左节点替代当前节点
                    AVLNode<K, V> des = cur;
                    while (des.left != null) {
                        des = des.left;
                    }

                    // 删除des节点
                    cur.right = delete(cur.right, des.k);

                    // 用des替代cur节点
                    des.left = cur.left;
                    des.right = cur.right;
                    cur = des;
                }
            }

            if (cur != null) {
                cur.h = calculateHeight(cur);
            }
            return reBalance(cur);
        }


        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            }

            if (key.compareTo(cur.k) < 0) {
                cur.left = add(cur.left, key, value);
            } else {
                cur.right = add(cur.right, key, value);
            }
            cur.h = calculateHeight(cur);
            return reBalance(cur);
        }

        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.left;
                } else {
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }

        /**
         * 右旋
         */
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            // update the cur node first,then the left node.
            cur.h = calculateHeight(cur);
            left.h = calculateHeight(left);
            return left;
        }

        /**
         * 左旋
         */
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;

            cur.h = calculateHeight(cur);
            right.h = calculateHeight(right);
            return right;
        }

        private int calculateHeight(AVLNode<K, V> cur) {
            return Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;
        }

        private int getHeight(AVLNode<K, V> cur) {
            return cur != null ? cur.h : 0;
        }

        /**
         * avl tree 旋转再平衡
         */
        private AVLNode<K, V> reBalance(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }

            int leftHeight = getHeight(cur.left);
            int rightHeight = getHeight(cur.right);
            // 需要再平衡的条件，左右高度差大于1
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {
                    // LL型，只需要右旋
                    if (getHeight(cur.left.left) >= getHeight(cur.left.right)) {
                        cur = rightRotate(cur);
                    } else {
                        // LR型，先左旋，后右旋
                        cur.left = leftRotate(cur.left);
                        cur = rightRotate(cur);
                    }
                } else {
                    // RR型，左旋
                    if (getHeight(cur.right.right) >= getHeight(cur.right.left)) {
                        cur = leftRotate(cur);
                    } else {
                        // RL型，先右旋，再左旋
                        cur.right = rightRotate(cur.right);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

    }

}
