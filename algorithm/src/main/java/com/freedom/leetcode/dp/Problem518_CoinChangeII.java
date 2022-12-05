package com.freedom.leetcode.dp;

/**
 * 518. Coin Change II
 *
 * @author tobebetter9527
 * @create 2022/12/05 20:17
 */
public class Problem518_CoinChangeII {

  public static int change(int amount, int[] coins) {
    return recursive(coins, 0, amount);
  }

  private static int recursive(int[] coins, int index, int restAmount) {
    if (restAmount == 0) {
      return 1;
    }
    if (index == coins.length) {
      return 0;
    }
    int ways = 0;
    for (int i = 0; i * coins[index] <= restAmount; i++) {
      ways += recursive(coins, index + 1, restAmount - i * coins[index]);
    }

    return ways;
  }

  public static int change2(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    // 从下到上，从右到左
    for (int col = amount; col >= 0; col--) {
      dp[n][col] = 0;
    }
    for (int row = n; row >= 0; row--) {
      dp[row][0] = 1;
    }

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = 1; restAmount <= amount; restAmount++) {
        int ways = 0;
        for (int i = 0; i * coins[index] <= restAmount; i++) {
          ways += dp[index + 1][restAmount - i * coins[index]];
        }
        dp[index][restAmount] = ways;
      }
    }

    return dp[0][amount];
  }

  public static int change3(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    // 从下到上，从右到左
    for (int col = amount; col >= 0; col--) {
      dp[n][col] = 0;
    }
    for (int row = n; row >= 0; row--) {
      dp[row][0] = 1;
    }

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = 1; restAmount <= amount; restAmount++) {
        dp[index][restAmount] = dp[index + 1][restAmount];
        if (restAmount - coins[index] >= 0) {
          dp[index][restAmount] += dp[index][restAmount - coins[index]];
        }
        //        int ways = 0;
        //        for (int i = 0; i * coins[index] <= restAmount; i++) {
        //          ways += dp[index + 1][restAmount - i * coins[index]];
        //        }
        //        dp[index][restAmount] = ways;
      }
    }

    return dp[0][amount];
  }


  public static int change4(int amount, int[] coins) {
    int n = coins.length;
    int[] dp = new int[amount + 1];
    // 从下到上，从右到左
    dp[0] = 1;

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = coins[index]; restAmount <= amount; restAmount++) {
        dp[restAmount] += dp[restAmount - coins[index]];

      }
    }

    return dp[amount];
  }


  public static void main(String[] args) {
    int amount = 5;
    int[] coins = {1, 2, 5};
    System.out.println(change4(amount, coins));
  }
}
