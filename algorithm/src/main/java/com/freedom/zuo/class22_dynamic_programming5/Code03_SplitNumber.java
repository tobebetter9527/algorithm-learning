package com.freedom.zuo.class22_dynamic_programming5;

/**
 * 给定一个正数n，求n的裂开方法数，
 * <p>
 * 规定：后面的数不能比前面的数小
 * <p>
 * 比如4的裂开方法有：
 * <p>
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * <p>
 * 5种，所以返回5
 *
 * @author tobebetter9527
 * @create 2022/07/22 22:01
 */
public class Code03_SplitNumber {

  /**
   * 暴力递归
   * <p>
   * 求裂开方法数，后面的数不能小于前面的数
   *
   * @param n 正数
   * @return 返回裂开的组合数
   */
  public static int ways(int n) {
    if (n < 1) {
      return 0;
    }

    return process(1, n);
  }

  /**
   * @param pre   前面裂开的数
   * @param restN 剩余的值
   */
  private static int process(int pre, int restN) {
    // 刚好找到裂开的数
    if (restN == 0) {
      return 1;
    }

    // 不够分，无效
    if (pre > restN) {
      return 0;
    }

    int ways = 0;
    // 后面裂开的数不能比前面的小
    for (int i = pre; i <= restN; i++) {
      ways += process(i, restN - i);
    }

    return ways;
  }

  // --------------------------------------------//

  /**
   * 动态规划
   * <p>
   * 求裂开方法数，后面的数不能小于前面的数
   *
   * @param n 正数
   * @return 返回裂开的组合数
   */
  public static int dp1(int n) {
    if (n < 1) {
      return 0;
    }

    int[][] dp = new int[n + 1][n + 1];
    dp[1][1] = 1;

    // 从下到上，从左到右
    for (int pre = n; pre > 0; pre--) {
      dp[pre][0] = 1;
      dp[pre][pre] = 1;

      for (int restN = pre + 1; restN <= n; restN++) {
        int ways = 0;
        // 后面裂开的数不能比前面的小
        for (int i = pre; i <= restN; i++) {
          ways += dp[i][restN - i];
        }

        dp[pre][restN] = ways;
      }
    }

    return dp[1][n];
  }

  // --------------------------------------------//

  /**
   * 动态规划
   * <p>
   * 求裂开方法数，后面的数不能小于前面的数
   *
   * @param n 正数
   * @return 返回裂开的组合数
   */
  public static int dp2(int n) {
    if (n < 1) {
      return 0;
    }

    int[][] dp = new int[n + 1][n + 1];
    dp[1][1] = 1;

    for (int pre = n; pre > 0; pre--) {
      dp[pre][0] = 1;
      dp[pre][pre] = 1;
    }

    // 从下到上，从左到右
    for (int pre = n - 1; pre > 0; pre--) {
      for (int restN = pre + 1; restN <= n; restN++) {
        dp[pre][restN] = dp[pre][restN - pre] + dp[pre + 1][restN];
      }
    }

    return dp[1][n];
  }

  // --------------------------------------------//

  public static void main(String[] args) {
    int testTimes = 10000;
    int maxValue = 20;

    for (int i = 0; i < testTimes; i++) {
      int n = (int) (Math.random() * maxValue);
      int ways = ways(n);
      int ways2 = dp1(n);
      int ways3 = dp2(n);
      if (ways != ways2 || ways != ways3) {
        System.out.println("n=" + n);
        System.out.println(ways);
        System.out.println(ways2);
        System.out.println(ways3);
        System.out.println("Opps!");
      }
    }
    System.out.println("done!");
  }
}
