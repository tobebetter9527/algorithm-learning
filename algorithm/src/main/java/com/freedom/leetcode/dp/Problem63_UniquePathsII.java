package com.freedom.leetcode.dp;

/**
 * 63. Unique Paths II
 *
 * @author tobebetter9527
 * @create 2022/12/03 8:23
 */
public class Problem63_UniquePathsII {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length - 1;
    int n = obstacleGrid[0].length - 1;
    return recursive(obstacleGrid, 0, 0, m, n);
  }

  private int recursive(int[][] obstacleGrid, int startM, int startN, int m, int n) {
    if (obstacleGrid[startM][startN] == 0) {
      if (startM == m && startN == n) {
        return 1;
      }
    } else {
      return 0;
    }

    // 往右边走
    int p1 =
        startN < n ? recursive(obstacleGrid, startM, startN + 1, m, n) : 0;

    // 往下走
    int p2 =
        startM < m ? recursive(obstacleGrid, startM + 1, startN, m, n) : 0;

    return p1 + p2;
  }

  /**
   * time complexity is O(m*n), space complexity is (O(m*n).
   *
   * @param obstacleGrid
   * @return
   */
  public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] dp = new int[m][n];
    dp[m - 1][n - 1] = obstacleGrid[m - 1][n - 1] == 0 ? 1 : 0;
    // 从下到上，从右到左
    for (int row = m - 2; row >= 0; row--) {
      dp[row][n - 1] = obstacleGrid[row][n - 1] == 0 ? dp[row + 1][n - 1] : 0;
    }
    for (int col = n - 2; col >= 0; col--) {
      dp[m - 1][col] = obstacleGrid[m - 1][col] == 0 ? dp[m - 1][col + 1] : 0;
    }

    for (int row = m - 2; row >= 0; row--) {
      for (int col = n - 2; col >= 0; col--) {
        int p1 = obstacleGrid[row][col] == 0 ? dp[row][col + 1] : 0;
        int p2 = obstacleGrid[row][col] == 0 ? dp[row + 1][col] : 0;
        dp[row][col] = p1 + p2;
      }
    }

    return dp[0][0];
  }


  public static void main(String[] args) {
    int m = 1, n = 2;
    int[] arr = {1, 0};
    int[][] obstacleGrid = new int[1][2];
    obstacleGrid[0] = arr;

    Problem63_UniquePathsII problem = new Problem63_UniquePathsII();
    int i = problem.uniquePathsWithObstacles2(obstacleGrid);
    System.out.println(i);
  }

}
