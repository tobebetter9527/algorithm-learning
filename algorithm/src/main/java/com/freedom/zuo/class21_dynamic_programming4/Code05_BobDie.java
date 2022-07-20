package com.freedom.zuo.class21_dynamic_programming4;

/**
 * 给定5个参数，N，M，row，col，k
 * <p>
 * 表示在NM的区域上，醉汉Bob初始在(row,col)位置
 * <p>
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * <p>
 * 任何时候Bob只要离开NM的区域，就直接死亡
 * <p>
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author tobebetter9527
 * @create 2022/07/20 21:33
 */
public class Code05_BobDie {

  /**
   * 暴力递归
   *
   * @param row 行
   * @param col 列
   * @param k   k步
   * @param n   共N列
   * @param m   共M行
   * @return
   */
  public static double livePosibility1(int row, int col, int k, int m, int n) {
    return process(row, col, k, n, m) / Math.pow(4, k);
  }

  private static int process(int row, int col, int restK, int m, int n) {
    // 掉出去了，0种可能
    if (row < 0 || row == m || col < 0 || col == n) {
      return 0;
    }

    // 没掉出去，1种可能
    if (restK == 0) {
      return 1;
    }

    // 往上下左右走
    int p1 = process(row, col - 1, restK - 1, m, n);
    int p2 = process(row, col + 1, restK - 1, m, n);
    int p3 = process(row - 1, col, restK - 1, m, n);
    int p4 = process(row + 1, col, restK - 1, m, n);

    return p1 + p2 + p3 + p4;
  }

  // ----------------------------------------------------- //

  /**
   * 动态规划
   *
   * @param row 行
   * @param col 列
   * @param k   k步
   * @param n   共N列
   * @param m   共M行
   * @return
   */
  public static double livePosibility2(int row, int col, int k, int m, int n) {
    int[][][] dp = new int[m][n][k + 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j][0] = 1;
      }
    }

    // 1到k
    for (int restK = 1; restK <= k; restK++) {
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          // 往上下左右走
          int p1 = pick(i, j - 1, restK - 1, m, n, dp);
          int p2 = pick(i, j + 1, restK - 1, m, n, dp);
          int p3 = pick(i - 1, j, restK - 1, m, n, dp);
          int p4 = pick(i + 1, j, restK - 1, m, n, dp);
          dp[i][j][restK] = p1 + p2 + p3 + p4;
        }
      }
    }

    return dp[row][col][k] / Math.pow(4, k);
  }

  private static int pick(int row, int col, int restK, int m, int n, int[][][] dp) {
    if (row < 0 || row == m || col < 0 || col == n) {
      return 0;
    }
    return dp[row][col][restK];
  }


  public static void main(String[] args) {
    System.out.println(livePosibility1(6, 6, 10, 50, 50));
    System.out.println(livePosibility2(6, 6, 10, 50, 50));
  }

}
