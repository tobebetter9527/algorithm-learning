package com.freedom.zuo.class15_union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 305
 *
 * @author tobebetter9527
 * @create 2022/07/10 9:47
 */
public class Code03_NumberOfIslandsII {

    public static List<Integer> numIslands21(int row, int col, int[][] positions) {
        List<Integer> list = new LinkedList<>();
        UnionFind1 uf = new UnionFind1(row, col);
        for (int[] position : positions) {
            list.add(uf.connect(position[0], position[1]));
        }
        return list;
    }

    // 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    // -------------------------------------//

    static class UnionFind1 {

        private int[] parents;
        private int[] size;
        private int[] stack;
        private int sets;
        private int row;
        private int col;

        public UnionFind1(int row, int col) {
            this.row = row;
            this.col = col;
            int length = row * col;

            parents = new int[length];
            size = new int[length];
            stack = new int[length];
        }

        public int connect(int row, int col) {
            int index = handleIndex(row, col);
            // 等于0，说明该位置没有被初始化过
            if (size[index] == 0) {
                parents[index] = index;
                size[index] = 1;
                sets++;
                union(row, col, row, col - 1);
                union(row, col, row, col + 1);
                union(row, col - 1, row, col);
                union(row, col + 1, row, col);
            }
            return sets;
        }

        private void union(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || row1 == row || col1 < 0 || col1 == col || row2 < 0 || row2 == row || col2 < 0 || col2 == col) {
                return;
            }
            int index1 = handleIndex(row1, col1);
            int index2 = handleIndex(row2, col2);
            // 说明有节点为0，不需要进行合并，两个都为1才进行合并
            if (size[index1] == 0 || size[index2] == 0) {
                return;
            }

            int f1 = findFather(index1);
            int f2 = findFather(index2);
            if (f1 != f2) {
                int f1Size = size[f1];
                int f2Size = size[f2];
                if (f1Size >= f2Size) {
                    parents[index2] = index1;
                    size[index1] = f1Size + f2Size;
                } else {
                    parents[index1] = index2;
                    size[index2] = f1Size + f2Size;
                }
                sets--;
            }
        }

        private int findFather(int cur) {
            int hi = 0;
            while (cur != parents[cur]) {
                stack[hi++] = cur;
                cur = parents[cur];
            }

            while (hi > 0) {
                parents[stack[--hi]] = cur;
            }
            return cur;
        }

        private int handleIndex(int row, int col) {
            return this.col * row + col;
        }
    }

    public static class UnionFind2 {

        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> help;
        private int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            help.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = big == f1 ? f2 : f1;
                    parent.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }

        /**
         * 17,20  ->  17_20
         */
        public int connect(int r, int c) {
            String key = r + "_" + c;
            if (!parent.containsKey(key)) {
                parent.put(key, key);
                size.put(key, 1);
                sets++;
                String up = (r - 1) + "_" + c;
                String down = (r + 1) + "_" + c;
                String left = r + "_" + (c - 1);
                String right = r + "_" + (c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
            }
            return sets;
        }
    }
}
