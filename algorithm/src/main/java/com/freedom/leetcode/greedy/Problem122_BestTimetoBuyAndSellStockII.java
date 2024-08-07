package com.freedom.leetcode.greedy;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * @author tobebetter9527
 * @create 2022/11/22 21:00
 */
public class Problem122_BestTimetoBuyAndSellStockII {

    public static void main(String[] args) {
        int[] prices = {7, 6, 4, 3, 1};
        Problem122_BestTimetoBuyAndSellStockII problem = new Problem122_BestTimetoBuyAndSellStockII();
        int maxProfit = problem.maxProfit(prices);
        System.out.println(maxProfit);
    }

    /**
     * time complexity is O(n), space complexity is O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int preValue = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (preValue < prices[i]) {
                profit += (prices[i] - preValue);
            }
            preValue = prices[i];
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }
}
