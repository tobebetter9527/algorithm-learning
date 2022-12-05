package com.freedom.leetcode.dp;

/**
 * 322. Coin Change
 *
 * @author tobebetter9527
 * @create 2022/12/05 23:35
 */
public class Problem322_Coin_Change {

  public static int coinChange(int[] coins, int amount) {
    return recursive(coins, 0, amount);
  }

  private static int recursive(int[] coins, int index, int restAmount) {
    if (restAmount == 0) {
      return 0;
    }
    if (index == coins.length) {
      return -1;
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i * coins[index] <= restAmount; i++) {
      int p1 = recursive(coins, index + 1, restAmount - i * coins[index]);
      if (p1 != -1) {
        min = Math.min(min, i + p1);
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public static void main(String[] args) {
    int[] coins = {1, 2, 5};
    int amount = 11;
    System.out.println(coinChange(coins, amount));
  }
}
