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


  public static void main(String[] args) {
    int[] prices = {7,6,4,3,1};
    System.out.println(maxProfit(prices));
  }
}
