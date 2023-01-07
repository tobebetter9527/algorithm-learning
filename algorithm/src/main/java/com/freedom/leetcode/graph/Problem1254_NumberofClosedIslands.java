package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1254. Number of Closed Islands
 *
 * @author tobebetter9527
 * @create 2023/01/07 20:21
 */
public class Problem1254_NumberofClosedIslands {

  public static int closedIsland(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          ans += dfs(grid, i, j, m, n);
        }
      }
    }
    return ans;
  }

  private static int dfs(int[][] grid, int i, int j, int m, int n) {
    // 越界，说明没有被包围的陆地，返回0
    if (i < 0 || i == m || j < 0 || j == n) {
      return 0;
    }
    // 碰到水，有可能是封闭的陆地
    if (grid[i][j] == 1) {
      return 1;
    }
    grid[i][j] = 1;
    // 这里可能有块封闭的陆地
    int ans = 1;
    // 上下左右任意一个方向越了边界，就不是封闭陆地
    ans = Math.min(ans, dfs(grid, i + 1, j, m, n));
    ans = Math.min(ans, dfs(grid, i - 1, j, m, n));
    ans = Math.min(ans, dfs(grid, i, j + 1, m, n));
    ans = Math.min(ans, dfs(grid, i, j - 1, m, n));
    return ans;
  }

  // ---- //

  public static int closedIsland2(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          ans += bfs(grid, i, j, m, n, dx, dy);
        }
      }
    }
    return ans;
  }

  private static int bfs(int[][] grid, int i, int j, int m, int n, int[] dx, int[] dy) {
    // 可能有一块封闭陆地空间
    int ans = 1;
    grid[i][j] = 1;
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
          if (grid[x][y] == 0) {
            grid[x][y] = 1;
            queueRow.add(x);
            queueCol.add(y);
          }
        } else {
          // 越界了，说明没有封闭陆地空间
          ans = 0;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] grid = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0},
        {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
    System.out.println(closedIsland2(grid));

  }
}
