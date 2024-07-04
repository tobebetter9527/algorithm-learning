package com.freedom.zuo.class08_trie_sort;

import java.util.HashMap;
import java.util.Map;

/**
 * prefix tree
 *
 * @author tobebetter9527
 * @create 2022/06/25 9:39
 */
public class Code02_TrieTree {

    // -----------------------------------------------------分割线--------------------------------------------//
    public static void main(String[] args) {
        int arrLen = 1000;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            String[] arr = generateArray(arrLen, strLen);
            for (int j = 0; j < arr.length; j++) {
                double random = Math.random();
                if (random < 0.25D) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (random < 0.5D) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (random < 0.75D) {
                    int search = trie1.search(arr[j]);
                    int search1 = trie2.search(arr[j]);
                    int search2 = right.search(arr[j]);
                    if (search != search1 || search != search2) {
                        System.out.println("There is a bug in search!");
                    }
                } else {
                    int search = trie1.prefixNumber(arr[j]);
                    int search1 = trie2.prefixNumber(arr[j]);
                    int search2 = right.prefixNumber(arr[j]);
                    if (search != search1 || search != search2) {
                        System.out.println("There is a bug in prefixNumber!");
                    }
                }
            }
        }
        System.out.println("done!");
    }

    private static String[] generateArray(int arrLen, int strLen) {
        int length = (int) (Math.random() * arrLen) + 1;
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = generateStr(strLen);
        }
        return arr;
    }

    // -----------------------------------------------------分割线--------------------------------------------//

    private static String generateStr(int strLen) {
        int length = (int) (Math.random() * strLen) + 1;
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            // 注意别超过字母的范围
            chars[i] = (char) ((int) (Math.random() * (26)) + 97);
        }
        return String.valueOf(chars);
    }

    static class Node1 {

        int pass;
        int end;
        // 用数组实现
        Node1[] nexts;

        public Node1() {
            this.nexts = new Node1[26];
        }
    }

    // -----------------------------------------------------分割线--------------------------------------------//

    static class Trie1 {

        Node1 root;

        public Trie1() {
            this.root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            // 没有该单词
            if (search(word) == 0) {
                return;
            }

            Node1 node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }


        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                // 说明没找到
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            // 找到几个
            return node.end;
        }

        /**
         * 前缀为pre的单词有几个
         *
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < pre.length(); i++) {
                path = pre.charAt(i) - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            // 跟search方法不一样的地方
            return node.pass;
        }
    }

    static class Node2 {

        int pass;
        int end;
        // 用哈希表实现
        Map<Integer, Node2> nexts;

        public Node2() {
            this.nexts = new HashMap<>(26);
        }
    }

    static class Trie2 {

        Node2 root;

        public Trie2() {
            this.root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node2 node = root;
            node.pass++;
            int path;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i);
                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node2());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            Node2 node = root;
            node.pass--;
            int path;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i);
                if (--node.nexts.get(path).pass == 0) {
                    node.nexts.remove(path);
                    return;
                }
                node = node.nexts.get(path);
            }
            node.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node2 node = root;
            int path;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i);
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            Node2 node = root;
            int path;
            for (int i = 0; i < pre.length(); i++) {
                path = pre.charAt(i);
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
    }

    static class Right {

        Map<String, Integer> map;

        public Right() {
            this.map = new HashMap<>();
        }

        public void insert(String word) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        public void delete(String word) {
            if (map.containsKey(word)) {
                if (map.get(word) == 1) {
                    map.remove(word);
                } else {
                    map.put(word, map.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return map.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int num = 0;
            for (String word : map.keySet()) {
                if (word.startsWith(pre)) {
                    num += map.get(word);
                }
            }
            return num;
        }
    }


}
