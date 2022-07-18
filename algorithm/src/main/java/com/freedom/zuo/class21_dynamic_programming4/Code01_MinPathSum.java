package com.freedom.zuo.class21_dynamic_programming4;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * <p>
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * <p>
 * 返回最小距离累加和
 *
 * @author tobebetter9527
 * @create 2022/07/18 21:13
 */
public class Code01_MinPathSum {

  /**
   * 暴力递归
   *
   * @param matrix
   * @return
   */
  public static int minPathSum1(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }

    return process(matrix, matrix.length - 1, matrix[0].length - 1);
  }

  /**
   * 来到(row,col)点的最小累加和为minsum
   *
   * @param matrix 矩阵
   * @param row    出发的row行
   * @param col    出发的col行
   */
  private static int process(int[][] matrix, int row, int col) {
    // 到达目标
    if (row == 0 && col == 0) {
      return matrix[0][0];
    }

    // 要么从往右走来到当前点
    int p1 = col - 1 >= 0 ? process(matrix, row, col - 1) + matrix[row][col] : Integer.MAX_VALUE;

    // 要么从往下走来到当前点
    int p2 = row - 1 >= 0 ? process(matrix, row - 1, col) + matrix[row][col] : Integer.MAX_VALUE;

    return Math.min(p1, p2);
  }

  // --------------------------------------------//

  /**
   * 动态规划
   *
   * @param matrix
   * @return
   */
  public static int minPathSum2(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    int[][] dp = new int[row][col];

    dp[0][0] = matrix[0][0];

    for (int i = 1; i < col; i++) {
      dp[0][i] = dp[0][i - 1] + matrix[0][i];
    }

    for (int i = 1; i < row; i++) {
      dp[i][0] = dp[i - 1][0] + matrix[i][0];
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
      }
    }

    return dp[row - 1][col - 1];
  }

  // --------------------------------------------//

  /**
   * 动态规划,压缩数组
   *
   * @param matrix
   * @return
   */
  public static int minPathSum3(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    int row = matrix.length;
    int col = matrix[0].length;

    int[] dp = new int[col];
    dp[0] = matrix[0][0];

    for (int i = 1; i < col; i++) {
      dp[i] = dp[i - 1] + matrix[0][i];
    }

    for (int i = 1; i < row; i++) {
      dp[0] = dp[0] + matrix[i][0];
      for (int j = 1; j < col; j++) {
        dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
      }
    }

    return dp[col - 1];
  }

  // --------------------------------------------//


  public static void main(String[] args) {
    int rowSize = 10;
    int colSize = 10;
    int[][] m = generateRandomMatrix(rowSize, colSize);
    printMatrix(m);
    System.out.println(minPathSum1(m));
    System.out.println(minPathSum2(m));
    System.out.println(minPathSum3(m));
    System.out.println(minPathSum4(m));

  }

  // for test
  public static int[][] generateRandomMatrix(int rowSize, int colSize) {
    if (rowSize < 0 || colSize < 0) {
      return null;
    }
    rowSize = (int) (Math.random() * rowSize);
    colSize = (int) (Math.random() * colSize);

    int[][] result = new int[rowSize][colSize];
    for (int i = 0; i != result.length; i++) {
      for (int j = 0; j != result[0].length; j++) {
        result[i][j] = (int) (Math.random() * 10);
      }
    }
    return result;
  }

  // for test
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i != matrix.length; i++) {
      for (int j = 0; j != matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }


  public static int minPathSum4(int[][] m) {
    if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
      return 0;
    }
    int row = m.length;
    int col = m[0].length;
    int[] dp = new int[col];
    dp[0] = m[0][0];
    for (int j = 1; j < col; j++) {
      dp[j] = dp[j - 1] + m[0][j];
    }
    for (int i = 1; i < row; i++) {
      dp[0] += m[i][0];
      for (int j = 1; j < col; j++) {
        dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
      }
    }
    return dp[col - 1];
  }
}
