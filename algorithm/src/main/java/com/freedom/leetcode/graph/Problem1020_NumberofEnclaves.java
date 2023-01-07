package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1020. Number of Enclaves
 *
 * @author tobebetter9527
 * @create 2023/01/07 21:21
 */
public class Problem1020_NumberofEnclaves {

  /**
   * time complexity is O(m*n),space complexity is O(m*n).
   *
   * @param grid
   * @return
   */
  public static int numEnclaves(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          int dfs = dfs(grid, i, j, m, n);
          if (dfs != -1) {
            ans += dfs;
          }
        }
      }
    }
    return ans;
  }

  private static int dfs(int[][] grid, int i, int j, int m, int n) {
    // 表示没有飞地
    if (i < 0 || i == m || j < 0 || j == n) {
      return -1;
    }
    // 遇到水返回0
    if (grid[i][j] == 0) {
      return 0;
    }
    grid[i][j] = 0;
    int p1 = dfs(grid, i + 1, j, m, n);
    int p2 = dfs(grid, i - 1, j, m, n);
    int p3 = dfs(grid, i, j + 1, m, n);
    int p4 = dfs(grid, i, j - 1, m, n);
    if (p1 == -1 || p2 == -1 || p3 == -1 || p4 == -1) {
      return -1;
    }
    return p1 + p2 + p3 + p4 + 1;
  }

  // --- //

  public static int numEnclaves2(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          ans += bfs(grid, i, j, m, n, dx, dy);
        }
      }
    }
    return ans;
  }

  private static int bfs(int[][] grid, int i, int j, int m, int n, int[] dx, int[] dy) {
    grid[i][j] = 0;
    int ans = 1;
    boolean isBoundary = false;
    Queue<Integer> queueRow = new LinkedList<>();
    Queue<Integer> queueCol = new LinkedList<>();
    queueRow.add(i);
    queueCol.add(j);
    while (!queueRow.isEmpty()) {
      int row = queueRow.poll();
      int col = queueCol.poll();
      for (int k = 0; k < 4; k++) {
        int x = row + dx[k], y = col + dy[k];
        if (x >= 0 && x < m && y >= 0 && y < n) {
          if (grid[x][y] == 1) {
            queueRow.add(x);
            queueCol.add(y);
            ans++;
            grid[x][y] = 0;
          }
        } else {
          // 越界，说明没有飞地
          isBoundary = true;
        }
      }
    }
    return isBoundary ? 0 : ans;
  }

  public static void main(String[] args) {
    int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
    System.out.println(numEnclaves2(grid));
  }
}
