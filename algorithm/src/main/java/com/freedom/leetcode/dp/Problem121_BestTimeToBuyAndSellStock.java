package com.freedom.leetcode.dp;

/**
 * 121. Best Time to Buy and Sell Stock
 *
 * @author tobebetter9527
 * @create 2022/12/08 22:03
 */
public class Problem121_BestTimeToBuyAndSellStock {

  public static int maxProfit(int[] prices) {
    int max = 0;
    int n = prices.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        max = Math.max(max, prices[j] - prices[i]);
      }
    }
    return max;
  }

  /**
   * greedy, time complexity is O(n), space complexity is O(1)
   *
   * @param prices
   * @return
   */
  public static int maxProfit2(int[] prices) {
    int profit = 0;
    int minPrice = prices[0];
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] < minPrice) {
        minPrice = prices[i];
      } else if (prices[i] > minPrice) {
        profit = Math.max(profit, prices[i] - minPrice);
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    int[] prices = {7, 6, 4, 3, 1};
    System.out.println(maxProfit2(prices));
  }
}
