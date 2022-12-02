package com.freedom.leetcode.dp;

/**
 * Unique Paths
 *
 * @author tobebetter9527
 * @create 2022/12/02 21:48
 */
public class Problem62_UniquePaths {

  public int uniquePaths(int m, int n) {
    return recursive(0, 0, m - 1, n - 1);
  }

  private int recursive(int restM, int restN, int m, int n) {
    if (restM == m && restN == n) {
      return 1;
    }
    // 往右走
    int p1 = restN < n ? recursive(restM, restN + 1, m, n) : 0;

    // 往下走
    int p2 = restM < m ? recursive(restM + 1, restN, m, n) : 0;

    return p1 + p2;
  }

  /**
   * time complexity is O(m*n), space complexity is O(m*n)
   *
   * @param m
   * @param n
   * @return
   */
  public int uniquePaths2(int m, int n) {
    int[][] dp = new int[m][n];
    dp[m - 1][n - 1] = 1;

    // 从下到上，从左到右
    for (int row = m - 2; row >= 0; row--) {
      dp[row][n - 1] = dp[row + 1][n - 1];
    }
    for (int col = n - 2; col >= 0; col--) {
      dp[m - 1][col] = dp[m - 1][col + 1];
    }

    for (int row = m - 2; row >= 0; row--) {
      for (int col = n - 2; col >= 0; col--) {
        dp[row][col] = dp[row][col + 1] + dp[row + 1][col];
      }
    }

    return dp[0][0];
  }


  public static void main(String[] args) {
    int m = 1;
    int n = 1;
    Problem62_UniquePaths problem = new Problem62_UniquePaths();
    int i = problem.uniquePaths(m, n);
    int i1 = problem.uniquePaths2(m, n);
    System.out.println(i);
    System.out.println(i1);
  }
}
