package com.freedom.leetcode.dp;

/**
 * 279. Perfect Squares
 *
 * @author tobebetter9527
 * @create 2022/12/06 22:08
 */
public class Problem279_PerfectSquares {

  public static int numSquares(int n) {
    double sqrt = Math.sqrt(n);
    return recursive((int) Math.sqrt(n), n);
  }

  private static int recursive(int sqrt, int restN) {
    if (restN == 0) {
      return 0;
    }

    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= sqrt; i++) {
      if (restN >= i * i) {
        min = Math.min(min, 1 + recursive(sqrt, restN - i * i));
      }
    }

    return min == Integer.MAX_VALUE ? 0 : min;
  }

  public static int numSquares2(int n) {
    int sqrt = (int) Math.sqrt(n);
    int[] dp = new int[n + 1];
    dp[0] = 0;

    for (int restN = 1; restN <= n; restN++) {
      int min = Integer.MAX_VALUE;
      for (int i = 1; i <= sqrt; i++) {
        if (restN >= i * i) {
          min = Math.min(min, 1 + dp[restN - i * i]);
        }
      }
      dp[restN] = min == Integer.MAX_VALUE ? 0 : min;
    }
    return dp[n];
  }

  public static int numSquares3(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;

    for (int restN = 1; restN <= n; restN++) {
      int min = Integer.MAX_VALUE;
      for (int i = 1; i * i <= restN; i++) {
        min = Math.min(min, 1 + dp[restN - i * i]);
      }
      dp[restN] = min == Integer.MAX_VALUE ? 0 : min;
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int n = 13;
    System.out.println(numSquares3(n));
  }
}
