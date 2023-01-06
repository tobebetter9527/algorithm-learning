package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Number of Islands
 *
 * @author tobebetter9527
 * @create 2023/01/06 22:04
 */
public class Problem200_NumberOfIslands {

  /**
   * time complexity is O(m * n), space complexity is O(m * n）
   *
   * @param grid
   * @return
   */
  public static int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ans++;
          dfs(grid, i, j, m, n);
        }
      }
    }
    return ans;
  }

  private static void dfs(char[][] grid, int i, int j, int m, int n) {
    if (i < 0 || i == m || j < 0 || j == n || grid[i][j] == '0') {
      return;
    }
    grid[i][j] = '0';
    dfs(grid, i + 1, j, m, n);
    dfs(grid, i - 1, j, m, n);
    dfs(grid, i, j + 1, m, n);
    dfs(grid, i, j - 1, m, n);
  }

  // -------- //

  public static int numIslands2(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ans++;
          bfs2(grid, i, j, m, n);
        }
      }
    }

    return ans;
  }

  private static void bfs(char[][] grid, int i, int j, int m, int n) {
    grid[i][j] = '0';
    Queue<Integer> queue = new LinkedList<>();
    queue.add(i * n + j);
    while (!queue.isEmpty()) {
      int poll = queue.remove();
      int row = poll / n;
      int col = poll % n;
      if (row - 1 >= 0 && grid[row - 1][col] == '1') {
        queue.add((row - 1) * n + col);
        grid[row - 1][col] = '0';
      }
      if (row + 1 < m && grid[row + 1][col] == '1') {
        queue.add((row + 1) * n + col);
        grid[row + 1][col] = '0';
      }
      if (col - 1 >= 0 && grid[row][col - 1] == '1') {
        queue.add(row * n + col - 1);
        grid[row][col - 1] = '0';
      }
      if (col + 1 < n && grid[row][col + 1] == '1') {
        queue.add(row * n + col + 1);
        grid[row][col + 1] = '0';
      }
    }
  }

  /**
   * 这种写法有问题，运行超时。
   *
   * @param grid
   * @param i
   * @param j
   * @param m
   * @param n
   */
  private static void bfs2(char[][] grid, int i, int j, int m, int n) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(i * n + j);
    while (!queue.isEmpty()) {
      int poll = queue.poll();
      int row = poll / n;
      int col = poll % n;
      grid[row][col] = '0';
      if (row - 1 >= 0 && grid[row - 1][col] == '1') {
        queue.add((row - 1) * n + col);
      }
      if (row + 1 < m && grid[row + 1][col] == '1') {
        queue.add((row + 1) * n + col);
      }
      if (col - 1 >= 0 && grid[row][col - 1] == '1') {
        queue.add(row * n + col - 1);
      }
      if (col + 1 < n && grid[row][col + 1] == '1') {
        queue.add(row * n + col + 1);
      }
    }
  }

  // ------ //

  public static int numIslands3(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length, n = grid[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int ans = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ans++;
          bfs3(grid, i, j, m, n, dx, dy);
        }
      }
    }

    return ans;
  }

  private static void bfs3(char[][] grid, int i, int j, int m, int n, int[] dx, int[] dy) {
    grid[i][j] = '0';
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i, j});
    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      int row = poll[0];
      int col = poll[1];
      for (int k = 0; k < 4; k++) {
        int x = row + dx[k], y = col + dy[k];
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
          queue.add(new int[]{x, y});
          grid[x][y] = '0';
        }
      }
    }
  }


  public static void main(String[] args) {
    //    char[][] grid = {
    //        {'1', '1', '0', '0', '0'},
    //        {'1', '1', '0', '0', '0'},
    //        {'0', '0', '1', '0', '0'},
    //        {'0', '0', '0', '1', '1'}
    //    };

    char[][] grid = {
        {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
        {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
        {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
        {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
        {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};

    System.out.println(numIslands3(grid));


  }
}
