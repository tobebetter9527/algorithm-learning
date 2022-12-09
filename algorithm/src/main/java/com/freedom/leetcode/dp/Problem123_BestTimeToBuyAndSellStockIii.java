package com.freedom.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 123. Best Time to Buy and Sell Stock III
 *
 * @author tobebetter9527
 * @create 2022/12/09 21:42
 */
public class Problem123_BestTimeToBuyAndSellStockIii {

  public static int maxProfit(int[] prices) {
    Map<String, Integer> map = new HashMap<>();
    return recursive(prices, 0, true, 2, map);
  }

  /**
   * 虽然可行，但是参数多，比较复杂
   *
   * @param prices
   * @param index   当前索引位置
   * @param isBuy   是只能买true，还是只能卖false
   * @param txCount 交易次数
   * @return
   */
  private static int recursive(int[] prices, int index, boolean isBuy, int txCount, Map<String, Integer> map) {
    if (index == prices.length) {
      map.put("" + index + isBuy + txCount, 0);
      return 0;
    }
    if (txCount == 0) {
      map.put("" + index + isBuy + txCount, 0);
      return 0;
    }

    if (map.containsKey("" + index + isBuy + txCount)) {
      return map.get("" + index + isBuy + txCount);
    }

    int profit = Integer.MIN_VALUE;
    // 当前只能买
    if (isBuy) {
      // 买
      int p1 = -prices[index] + recursive(prices, index + 1, false, txCount, map);
      profit = Math.max(profit, p1);

      // 不买
      int p2 = recursive(prices, index + 1, true, txCount, map);
      profit = Math.max(profit, p2);
    } else {
      // 只能卖出
      // 卖
      int p1 = prices[index] + recursive(prices, index + 1, true, txCount - 1, map);
      profit = Math.max(profit, p1);
      // 不卖
      int p2 = recursive(prices, index + 1, false, txCount, map);
      profit = Math.max(profit, p2);
    }

    map.put("" + index + isBuy + txCount, profit);
    return profit;
  }


  public static int maxProfit2(int[] prices) {
    return recursive2(prices, 0, 0);
  }

  /**
   * 虽然可行，但是参数多，比较复杂
   *
   * @param prices
   * @param index  当前索引位置
   * @param status 0无操作，1第一买，2第一次卖，3第二买，4第二次卖
   * @return
   */
  private static int recursive2(int[] prices, int index, int status) {
    if (index == prices.length) {
      return 0;
    }
    if (status == 4) {
      return 0;
    }

    // 可以买的条件
    if (status == 0 || status == 2) {
      // 买
      int p1 = -prices[index] + recursive2(prices, index + 1, status + 1);

      // 不买
      int p2 = recursive2(prices, index + 1, status);

      return Math.max(p1, p2);
      // 只能卖 status == 1 || status == 3
    } else {
      // 卖
      int p1 = prices[index] + recursive2(prices, index + 1, status + 1);

      // 不卖
      int p2 = recursive2(prices, index + 1, status);

      return Math.max(p1, p2);
    }
  }

  public static int maxProfit3(int[] prices) {
    int n = prices.length;
    int statusN = 4;
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
        if (status == 0 || status == 2) {
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


  public static int maxProfit4(int[] prices) {
    int n = prices.length;
    int statusN = 4;
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
    int[] prices = {1, 2, 3, 4, 5};
    System.out.println(maxProfit3(prices));

  }

}
