package com.freedom.leetcode.dp;

/**
 * 123. Best Time to Buy and Sell Stock III
 *
 * @author tobebetter9527
 * @create 2022/12/09 21:42
 */
public class Problem188_BestTimeToBuyAndSellStockIIV {

  public int maxProfit(int k, int[] prices) {
    return recursive2(prices, 0, 0, k << 1);
  }

  /**
   * 虽然可行，但是参数多，比较复杂
   *
   * @param prices
   * @param index  当前索引位置
   * @param status 0无操作，1第一买，2第一次卖，3第二买，4第二次卖，以此类推
   * @return
   */
  private static int recursive2(int[] prices, int index, int status, int k) {
    if (index == prices.length) {
      return 0;
    }
    if (status == k) {
      return 0;
    }

    // 可以买的条件
    if ((status & 1) == 0) {
      // 买
      int p1 = -prices[index] + recursive2(prices, index + 1, status + 1, k);

      // 不买
      int p2 = recursive2(prices, index + 1, status, k);

      return Math.max(p1, p2);
      // 只能卖 status == 1 || status == 3
    } else {
      // 卖
      int p1 = prices[index] + recursive2(prices, index + 1, status + 1, k);

      // 不卖
      int p2 = recursive2(prices, index + 1, status, k);

      return Math.max(p1, p2);
    }
  }

  public static int maxProfit3(int k, int[] prices) {
    int n = prices.length;
    int statusN = k << 1;
    int[][] dp = new int[n + 1][statusN + 1];
    // 可以省略
    //    for (int i = 0; i <= status ; i++) {
    //      dp[n][i] = 0;
    //    }
    //    for (int i = n; i >= 0; i--) {
    //      dp[i][4] = 0;
    //    }

    // 从下到上，从右到左
    for (int index = n - 1; index >= 0; index--) {
      for (int status = statusN - 1; status >= 0; status--) {
        // 可以买的条件
        if ((status & 1) == 0) {
          // 买
          int p1 = -prices[index] + dp[index + 1][status + 1];

          // 不买
          int p2 = dp[index + 1][status];

          dp[index][status] = Math.max(p1, p2);
          // 只能卖 status == 1 || status == 3
        } else {
          // 卖
          int p1 = prices[index] + dp[index + 1][status + 1];

          // 不卖
          int p2 = dp[index + 1][status];

          dp[index][status] = Math.max(p1, p2);
        }
      }
    }

    return dp[0][0];
  }


  public static int maxProfit4(int k, int[] prices) {
    int n = prices.length;
    int statusN = k << 1;
    int[] dp = new int[statusN + 1];
    // 从下到上，从右到左
    for (int index = n - 1; index >= 0; index--) {
      for (int status = 0; status <= statusN - 1; status++) {
        // 可以买的条件status == 0 || status == 2
        if ((status & 1) == 0) {
          // 买
          int p1 = -prices[index] + dp[status + 1];

          // 不买
          int p2 = dp[status];

          dp[status] = Math.max(p1, p2);
          // 只能卖 status == 1 || status == 3
        } else {
          // 卖
          int p1 = prices[index] + dp[status + 1];

          // 不卖
          int p2 = dp[status];

          dp[status] = Math.max(p1, p2);
        }
      }
    }
    return dp[0];
  }


  public static void main(String[] args) {
    int[] prices = {2, 4, 1};
    int k = 2;
    //  System.out.println(maxProfit3(k, prices));

  }

}
