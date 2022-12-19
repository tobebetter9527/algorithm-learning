package com.freedom.leetcode.unoin_find;

/**
 * 547. Number of Provinces
 *
 * @author tobebetter9527
 * @create 2022/12/19 20:58
 */
public class Problem547_NumberOfProvinces {

  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    UnionFind unionFind = new UnionFind(n);
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

    int[] parents;
    int[] sizeMap;
    int sets;
    int[] stack;

    public UnionFind(int n) {
      parents = new int[n];
      sizeMap = new int[n];
      sets = n;
      stack = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
        sizeMap[i] = 1;
      }
    }

    public int findParent(int i) {
      int index = -1;
      while (i != parents[i]) {
        stack[++index] = i;
        i = parents[i];
      }

      while (index != -1) {
        int idx = stack[index--];
        parents[idx] = i;
      }
      return i;
    }

    public void union(int i, int j) {
      int ii = findParent(i);
      int jj = findParent(j);
      if (ii != jj) {
        int isize = sizeMap[ii];
        int jsize = sizeMap[jj];
        int big = isize > jsize ? ii : jj;
        int small = big == ii ? jj : ii;
        parents[small] = big;
        sizeMap[big] = isize + jsize;
        sizeMap[small] = 0;
        sets--;
      }
    }
  }
}
