package com.freedom.leetcode.dp;

/**
 * 343. Integer Break
 *
 * @author tobebetter9527
 * @create 2022/12/03 10:08
 */
public class Problem343_IntegerBreak {

  public int integerBreak(int n) {
    if (n <= 3) {
      return n - 1;
    }
    return recursive(1, n);
  }

  private int recursive(int startVal, int restN) {
    if (restN == 0) {
      return 1;
    }
    if (startVal == restN) {
      return startVal;
    }
    if (startVal > restN) {
      return 0;
    }
    int max = 1;
    for (int i = startVal; i <= restN; i++) {
      max = Math.max(max, i * recursive(i, restN - i));
    }

    return max;
  }

  public int integerBreak2(int n) {
    if (n <= 3) {
      return n - 1;
    }
    int[][] dp = new int[n + 1][n + 1];
    // 从下到上，从左到右
    for (int row = n; row > 0; row--) {
      dp[row][0] = 1;
      dp[row][row] = row;

      for (int restN = row + 1; restN <= n; restN++) {
        int max = 1;
        for (int i = row; i <= restN; i++) {
          max = Math.max(max, i * dp[i][restN - i]);
        }
        dp[row][restN] = max;
      }
    }

    return dp[1][n];
  }

  public static void main(String[] args) {
    int n = 10;
    Problem343_IntegerBreak problem = new Problem343_IntegerBreak();
    int i = problem.integerBreak2(n);
    System.out.println(i);
  }
}
