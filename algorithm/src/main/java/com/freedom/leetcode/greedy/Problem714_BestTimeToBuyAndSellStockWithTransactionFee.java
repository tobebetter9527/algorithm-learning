package com.freedom.leetcode.greedy;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 *
 * @author tobebetter9527
 * @create 2022/11/30 21:02
 */
public class Problem714_BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int buy = prices[0] + fee;
        for (int price : prices) {
            // 如果当前价格+交易费比之前的还低，更新买入价, 交易费就在第一买的时候付款了
            if (price + fee < buy) {
                buy = price + fee;
            } else if (price > buy) {
                profit += price - buy;
                // 可能下个价格比当前的更高，当前的价格并不是真正的卖出
                buy = price;
            }
        }

        return profit;
    }

}
