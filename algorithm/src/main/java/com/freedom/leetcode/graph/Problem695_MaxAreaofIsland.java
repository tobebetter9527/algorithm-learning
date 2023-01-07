package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 695. Max Area of Island
 *
 * @author tobebetter9527
 * @create 2023/01/07 10:10
 */
public class Problem695_MaxAreaofIsland {

  /**
   * time complexity is O(m×n),space complexity is O(m×n).
   *
   * @param grid
   * @return
   */
  public static int maxAreaOfIsland(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int max = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          max = Math.max(max, dfs(grid, i, j, m, n));
        }
      }
    }
    return max;
  }

  private static int dfs(int[][] grid, int i, int j, int m, int n) {
    if (i < 0 || i == m || j < 0 || j == n || grid[i][j] == 0) {
      return 0;
    }
    grid[i][j] = 0;
    int ans = 1;
    ans += dfs(grid, i + 1, j, m, n);
    ans += dfs(grid, i - 1, j, m, n);
    ans += dfs(grid, i, j + 1, m, n);
    ans += dfs(grid, i, j - 1, m, n);
    return ans;
  }

  // -- //

  public static int maxAreaOfIsland2(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int max = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          max = Math.max(max, bfs(grid, i, j, m, n, dx, dy));
        }
      }
    }
    return max;
  }

  private static int bfs(int[][] grid, int i, int j, int m, int n, int[] dx, int[] dy) {
    int ans = 1;
    grid[i][j] = 0;
    Queue<Integer> queuex = new LinkedList<>();
    Queue<Integer> queuey = new LinkedList<>();
    queuex.add(i);
    queuey.add(j);
    while (!queuex.isEmpty()) {
      int row = queuex.poll();
      int col = queuey.poll();
      for (int k = 0; k < 4; k++) {
        int x = row + dx[k], y = col + dy[k];
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
          ans++;
          queuex.add(x);
          queuey.add(y);
          grid[x][y] = 0;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
    System.out.println(maxAreaOfIsland2(grid));
  }

}
