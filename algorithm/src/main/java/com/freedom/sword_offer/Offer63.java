package com.freedom.sword_offer;

public class Offer63 {

  public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
      return 0;
    }
    int profit = 0, pre = prices[0];
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > pre) {
        profit = Math.max(profit, (prices[i] - pre));
      } else {
        pre = prices[i];
      }
    }
    return profit;
  }
}
