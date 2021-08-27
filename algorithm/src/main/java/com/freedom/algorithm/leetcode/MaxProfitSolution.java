package com.freedom.algorithm.leetcode;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell
 * one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy
 * again).
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4] Output: 7 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit =
 * 5-1 = 4. Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3. Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5] Output: 4 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
 * 5-1 = 4. Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple
 * transactions at the same time. You must sell before buying again. Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1] Output: 0 Explanation: In this case, no transaction is done, i.e., max profit = 0.  
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 3 * 104 0 <= prices[i] <= 104
 * <p>
 * 作者：力扣 (LeetCode) 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/ 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author tobebetter
 */
public class MaxProfitSolution {

  /**
   * 思路：低买高卖
   */
  public static int maxProfit(int[] nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    int temp = 0;
    for (int i = 0; i < nums.length; i++) {
      // 寻找局部最小值
      while ((i + 1) < nums.length && nums[i] > nums[i + 1]) {
        i++;
      }
      // 如果数组边界下降趋势，则中断
      if ((i + 1) == nums.length - 1 && nums[i] > nums[i + 1]) {
        break;
      }
      temp -= nums[i];

      // 寻找局部最大值
      while ((i + 1) < nums.length && nums[i] < nums[i + 1]) {
        i++;
      }
      // 如果数组边界是上升趋势，则取最后一个值卖出，否则就是局部最大值
      if ((i + 1) == nums.length - 1 && nums[i] < nums[i + 1]) {
        temp += nums[i + 1];
      } else {
        temp += nums[i];
      }
    }
    return temp;
  }


  public static void main(String[] args) {
    int[] nums = {7, 8, 8, 8, 9, 1, 1, 0};
    System.out.println(maxProfit(nums));
  }
}
