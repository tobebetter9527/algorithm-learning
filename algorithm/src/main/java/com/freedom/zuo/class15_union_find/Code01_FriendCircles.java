package com.freedom.zuo.class15_union_find;


/**
 * leetcode 547. Number of Provinces
 *
 * @author tobebetter9527
 * @create 2022/07/09 10:58
 */
public class Code01_FriendCircles {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        // 画矩阵，只有遍历右半上部分即可
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }


    static class UnionFind {

        // parents[i] = k; 表示i的父节点是k
        private int[] parents;
        // size[i] = k, i节点所在集合的大小
        private int[] size;
        // 辅助栈
        private int[] stack;
        // 集合大小
        private int sets;

        public UnionFind(int n) {
            parents = new int[n];
            size = new int[n];
            stack = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public void union(int i, int j) {
            int pi = findFather(i);
            int pk = findFather(j);
            if (pi != pk) {
                // 谁大谁合并谁
                int big = size[pi] >= size[pk] ? pi : pk;
                int small = size[pi] < size[pk] ? pi : pk;
                parents[small] = big;
                size[big] = size[big] + size[small];
                size[small] = 0;

                sets--;
            }
        }

        private int findFather(int i) {
            int index = 0;
            while (i != parents[i]) {
                stack[index++] = i;
                i = parents[i];
            }

            int k;
            while (index > 0) {
                k = stack[--index];
                parents[k] = i;
            }

            return i;
        }
    }


}
