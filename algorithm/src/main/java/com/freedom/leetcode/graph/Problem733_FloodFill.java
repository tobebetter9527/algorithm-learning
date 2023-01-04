package com.freedom.leetcode.graph;

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

  public static void main(String[] args) {
    int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    int sr = 1;
    int sc = 1;
    int color = 2;
    Problem733_FloodFill problem = new Problem733_FloodFill();
    int[][] ints = problem.floodFill(image, sr, sc, color);
    System.out.println(ints);
  }

}
