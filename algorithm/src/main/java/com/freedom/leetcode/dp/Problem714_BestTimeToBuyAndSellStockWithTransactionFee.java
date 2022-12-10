package com.freedom.leetcode.dp;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 *
 * @author tobebetter9527
 * @create 2022/11/30 21:02
 */
public class Problem714_BestTimeToBuyAndSellStockWithTransactionFee {

  public static int maxProfit(int[] prices, int fee) {
    return recursive(prices, 0, 0, fee);
  }

  private static int recursive(int[] prices, int index, int isBuyOrSell, int fee) {
    if (index == prices.length) {
      return 0;
    }
    if (isBuyOrSell == 0) {
      // buy
      int p1 = -prices[index] + recursive(prices, index + 1, isBuyOrSell + 1, fee);
      // not buy
      int p2 = recursive(prices, index + 1, isBuyOrSell, fee);
      return Math.max(p1, p2);
    } else {
      // sell
      int p1 = prices[index] - fee + recursive(prices, index + 1, isBuyOrSell - 1, fee);
      // not sell
      int p2 = recursive(prices, index + 1, isBuyOrSell, fee);
      return Math.max(p1, p2);
    }
  }

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param prices
   * @param fee
   * @return
   */
  public static int maxProfit2(int[] prices, int fee) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];

    for (int index = n - 1; index >= 0; index--) {
      for (int isBuyOrSell = 0; isBuyOrSell <= 1; isBuyOrSell++) {
        if (isBuyOrSell == 0) {
          // buy
          int p1 = -prices[index] + dp[index + 1][isBuyOrSell + 1];
          // not buy
          int p2 = dp[index + 1][isBuyOrSell];
          dp[index][isBuyOrSell] = Math.max(p1, p2);
        } else {
          // sell
          int p1 = prices[index] - fee + dp[index + 1][isBuyOrSell - 1];
          // not sell
          int p2 = dp[index + 1][isBuyOrSell];
          dp[index][isBuyOrSell] = Math.max(p1, p2);
        }
      }
    }
    return dp[0][0];
  }


  public static void main(String[] args) {
    int[] prices = {1, 3, 7, 5, 10, 3};
    int fee = 3;
    System.out.println(maxProfit2(prices, fee));

  }

}
