package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 733. Flood Fill
 *
 * @author tobebetter9527
 * @create 2023/01/01 13:58
 */
public class Problem733_FloodFill {

  int m;
  int n;

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    m = image.length;
    n = image[0].length;
    int value = image[sr][sc];
    boolean[][] visit = new boolean[m][n];
    infect(image, sr, sc, color, visit, value);
    return image;
  }

  private void infect(int[][] image, int row, int col, int color, boolean[][] visit, int value) {
    if (row < 0 || row == m || col < 0 || col == n || visit[row][col] || image[row][col] != value) {
      return;
    }
    image[row][col] = color;
    visit[row][col] = true;
    infect(image, row + 1, col, color, visit, value);
    infect(image, row - 1, col, color, visit, value);
    infect(image, row, col + 1, color, visit, value);
    infect(image, row, col - 1, color, visit, value);
  }

  // ------- //

  /**
   * time complexity is O(m * n),space complexity is O(m * n).
   *
   * @param image
   * @param sr
   * @param sc
   * @param color
   * @return
   */
  public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
    // 相同颜色，不需要更改
    int curColor = image[sr][sc];
    if (curColor == color) {
      return image;
    }

    int m = image.length, n = image[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{sr, sc});
    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      // 先覆盖，避免被再次访问到
      image[poll[0]][poll[1]] = color;
      for (int i = 0; i < 4; i++) {
        int mx = poll[0] + dx[i], ny = poll[1] + dy[i];
        if (mx >= 0 && mx < m && ny >= 0 && ny < n && image[mx][ny] == curColor) {
          queue.add(new int[]{mx, ny});
        }
      }
    }

    return image;
  }

  /**
   * time complexity is O(m * n),space complexity is O(m * n).
   *
   * @param image
   * @param sr
   * @param sc
   * @param color
   * @return
   */
  public int[][] floodFill3(int[][] image, int sr, int sc, int color) {
    // 相同颜色，不需要更改
    int curColor = image[sr][sc];
    if (curColor == color) {
      return image;
    }
    int m = image.length, n = image[0].length;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    image[sr][sc] = color;
    dfs(image, sr, sc, curColor, m, n, dx, dy, color);
    return image;
  }

  private void dfs(int[][] image, int sr, int sc, int curColor, int m, int n, int[] dx, int[] dy, int color) {
    for (int i = 0; i < 4; i++) {
      int mx = sr + dx[i], ny = sc + dy[i];
      if (mx >= 0 && mx < m && ny >= 0 && ny < n && image[mx][ny] == curColor) {
        image[mx][ny] = color;
        dfs(image, mx, ny, curColor, m, n, dx, dy, color);
      }
    }
  }

  public static void main(String[] args) {
    int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    int sr = 1;
    int sc = 1;
    int color = 2;
    Problem733_FloodFill problem = new Problem733_FloodFill();
    int[][] ints = problem.floodFill3(image, sr, sc, color);
    System.out.println(ints);
  }

}
