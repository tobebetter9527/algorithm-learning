package com.freedom.sword_offer;

public class Offer47 {

  public int maxValue(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0]= grid[0][0];
    for (int row = 1; row < m; row++) {
      dp[row][0] = dp[row - 1][0] + grid[row][0];
    }
    for (int col = 1; col < n; col++) {
      dp[0][col] = dp[0][col - 1] + grid[0][col];
    }
    for (int row = 1; row < m; row++) {
      for (int col = 1; col < n; col++) {
        dp[row][col] = Math.max(dp[row -1][col] + grid[row][col] , dp[row][col-1] + grid[row][col]);
      }
    }
    return dp[m-1][n-1];
  }
}
