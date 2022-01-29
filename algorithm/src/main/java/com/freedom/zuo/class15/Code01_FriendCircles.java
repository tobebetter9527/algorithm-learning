package com.freedom.zuo.class15;

/**
 * 本题为leetcode原题 , 测试链接：https://leetcode.com/problems/friend-circles/ ,可以直接通过
 *
 * @author tobebetter9527
 * @create 2022/01/29 20:20
 */
public class Code01_FriendCircles {


  public static int findCircleNum(int[][] M) {
    int N = M.length;
    UnionFind unionFind = new UnionFind(N);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (M[i][j] == 1) {
          unionFind.union(i, j);
        }
      }
    }
    return unionFind.sets();
  }

  public static class UnionFind {

    private int[] parents;
    private int[] size;
    private int[] help;
    private int sets;

    public UnionFind(int N) {
      parents = new int[N];
      size = new int[N];
      help = new int[N];
      sets = N;
      for (int i = 0; i < N; i++) {
        parents[i] = i;
        size[i] = 1;
      }
    }

    private int find(int i) {
      int h = 0;
      while (i != parents[i]) {
        help[h++] = i;
        i = parents[i];
      }

      for (h--; h >= 0; h--) {
        parents[help[h]] = i;
      }

      return i;
    }

    public void union(int i, int j) {
      int f1 = find(i);
      int f2 = find(j);
      if (f1 != f2) {
        if (size[f1] >= size[f2]) {
          size[f1] += size[f2];
          parents[f2] = f1;
        } else {
          size[f2] += size[f1];
          parents[f1] = f2;
        }
        sets--;
      }
    }

    public int sets() {
      return sets;
    }
  }
}
