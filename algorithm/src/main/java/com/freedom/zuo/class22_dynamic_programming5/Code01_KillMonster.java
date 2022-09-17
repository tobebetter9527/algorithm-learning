package com.freedom.zuo.class22_dynamic_programming5;

/**
 * 给定3个参数，n，m，k
 * <p>
 * 怪兽有n滴血，等着英雄来砍自己
 * <p>
 * 英雄每一次打击，都会让怪兽流失[0~m]的血量
 * <p>
 * 到底流失多少？每一次在[0~m]上等概率的获得一个值
 * <p>
 * 求k次打击之后，英雄把怪兽砍死的概率
 *
 * @author tobebetter9527
 * @create 2022/07/21 20:46
 */
public class Code01_KillMonster {

  /**
   * 暴力递归
   *
   * @param n n滴血
   * @param m 打击一次，掉0-m的血量
   * @param k 共k次打击
   * @return 打死的概率
   */
  public static double killMonster1(int n, int m, int k) {
    if (n < 1 || m < 1 || k < 1) {
      return 0;
    }

    // k次打击，血量的组合共有多少种组合, 每次0-m，共m+1种可能，k次就是（m+1)^k
    long all = (long) Math.pow(m + 1, k);
    long kill = process(k, m, n);
    return ((double) kill) / (double) all;
  }

  /**
   * 打死怪兽的组合有多少种
   *
   * @param restK 剩余打击次数
   * @param m     打击一次，掉0-m的血量
   * @param restN 剩余血量
   */
  private static long process(int restK, int m, int restN) {
    // 如果打击次数为0，看剩余血量
    if (restK == 0) {
      return restN <= 0 ? 1 : 0;
    }

    // 如果血量提前小于等于0，剩余的打击可能是（m+1)^restK种
    if (restN <= 0) {
      return (long) Math.pow(m + 1, restK);
    }

    long ways = 0;
    // 每次打击等概率掉0-m滴血
    for (int i = 0; i <= m; i++) {
      ways += process(restK - 1, m, restN - i);
    }

    return ways;
  }

  // ---------------------------------------------------- //

  /**
   * 动态规划
   *
   * @param n n滴血
   * @param m 打击一次，掉0-m的血量
   * @param k 共k次打击
   * @return 打死的概率
   */
  public static double dp1(int n, int m, int k) {
    if (n < 1 || m < 1 || k < 1) {
      return 0;
    }

    long[][] dp = new long[k + 1][n + 1];
    // 0行，除0，0点，其他点都是0，因为已经剩余0次打击， 但是怪物还有血，说明该种组合无效
    dp[0][0] = 1;

    // 从上到下，从左到有填
    for (int restK = 1; restK <= k; restK++) {

      // restN=0,怪物没血了，剩余打击产生多少种可能
      dp[restK][0] = (long) Math.pow(m + 1, restK);

      for (int restN = 1; restN <= n; restN++) {
        long ways = 0;
        // 每次打击等概率掉0-m滴血
        for (int i = 0; i <= m; i++) {
          // 如果怪物的血量为负数，直接计算
          if (restN - i >= 0) {
            ways += dp[restK - 1][restN - i];
          } else {
            // 这里注意restK-1，因为要打击一次，才变负数
            ways += (long) Math.pow(m + 1, restK - 1);
          }
        }
        dp[restK][restN] = ways;
      }
    }

    long all = (long) Math.pow(m + 1, k);
    return ((double) dp[k][n]) / (double) all;
  }

  // ---------------------------------------------------- //


  /**
   * 动态规划2,画图观察
   *
   * @param n n滴血
   * @param m 打击一次，掉0-m的血量
   * @param k 共k次打击
   * @return 打死的概率
   */
  public static double dp2(int n, int m, int k) {
    if (n < 1 || m < 1 || k < 1) {
      return 0;
    }

    long[][] dp = new long[k + 1][n + 1];
    // 0行，除0，0点，其他点都是0，因为已经剩余0次打击， 但是怪物还有血，说明该种组合无效
    dp[0][0] = 1;

    // 从上到下，从左到有填
    for (int restK = 1; restK <= k; restK++) {
      // restN=0,怪物没血了，剩余打击产生多少种可能
      dp[restK][0] = (long) Math.pow(m + 1, restK);

      for (int restN = 1; restN <= n; restN++) {
        //公式，但是要判断restN - 1 - m 是不是负数
        // dp[restK][restN] = dp[restK][restN - 1] + dp[restK - 1][restN] - dp[restK - 1][restN - 1 - m];
        dp[restK][restN] = dp[restK][restN - 1] + dp[restK - 1][restN];
        if (restN - 1 - m >= 0) {
          dp[restK][restN] -= dp[restK - 1][restN - 1 - m];
        } else {
          dp[restK][restN] -= (long) Math.pow(m + 1, restK - 1);
        }
      }
    }

    long all = (long) Math.pow(m + 1, k);
    return ((double) dp[k][n]) / (double) all;
  }


  public static void main(String[] args) {
    int NMax = 10;
    int MMax = 10;
    int KMax = 10;
    int testTime = 200;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int N = (int) (Math.random() * NMax);
      int M = (int) (Math.random() * MMax);
      int K = (int) (Math.random() * KMax);
      double ans1 = killMonster1(N, M, K);
      double ans2 = dp1(N, M, K);
      double ans3 = dp2(N, M, K);
      if (ans1 != ans2 || ans1 != ans3) {
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }

}
