package com.freedom.leetcode.dp;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 * @author tobebetter9527
 * @create 2022/12/10 8:49
 */
public class Problem309_BestTimeToBuyAandSellStockWithCooldown {


    public static int maxProfit(int[] prices) {
        return recursive(prices, 0, 0);
    }

    /**
     * @param index       current index position
     * @param isBuyOrSell 0->can buy,1->can sell.
     */
    private static int recursive(int[] prices, int index, int isBuyOrSell) {
        if (index >= prices.length) {
            return 0;
        }
        if (isBuyOrSell == 0) {
            // buy
            int p1 = -prices[index] + recursive(prices, index + 1, isBuyOrSell + 1);
            // not buy
            int p2 = recursive(prices, index + 1, isBuyOrSell);
            return Math.max(p1, p2);
        } else {
            // sell, index + 2, after you sell your stock,you can't buy stock on next day.
            int p1 = prices[index] + recursive(prices, index + 2, isBuyOrSell - 1);
            // not sell
            int p2 = recursive(prices, index + 1, isBuyOrSell);
            return Math.max(p1, p2);
        }
    }

    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        for (int index = n - 1; index >= 0; index--) {
            for (int isBuyOrSell = 0; isBuyOrSell <= 1; isBuyOrSell++) {
                if (isBuyOrSell == 0) {
                    // buy
                    int p1 = -prices[index] + dp[index + 1][isBuyOrSell + 1];
                    // not buy
                    int p2 = dp[index + 1][isBuyOrSell];
                    dp[index][isBuyOrSell] = Math.max(p1, p2);
                } else {
                    // sell, index + 2, after you sell your stock,you can't buy stock on next day.
                    int p1 = prices[index] + dp[index + 2][isBuyOrSell - 1];
                    // not sell
                    int p2 = dp[index + 1][isBuyOrSell];
                    dp[index][isBuyOrSell] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] prices = {1};
        System.out.println(maxProfit2(prices));
    }
}
