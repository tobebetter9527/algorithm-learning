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

  public static int coinChange2(int[] coins, int amount) {
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    // 从下到上，从左到右
    for (int col = amount; col > 0; col--) {
      dp[n][col] = -1;
    }
    for (int row = n; row >= 0; row--) {
      dp[row][0] = 0;
    }

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = 1; restAmount <= amount; restAmount++) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i * coins[index] <= restAmount; i++) {
          int p1 = dp[index + 1][restAmount - i * coins[index]];
          if (p1 != -1) {
            min = Math.min(min, i + p1);
          }
        }

        dp[index][restAmount] = min == Integer.MAX_VALUE ? -1 : min;
      }
    }
    return dp[0][amount];
  }

  public static int coinChange3(int[] coins, int amount) {
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    // 从下到上，从左到右
    for (int col = amount; col > 0; col--) {
      dp[n][col] = -1;
    }
    for (int row = n; row >= 0; row--) {
      dp[row][0] = 0;
    }

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = 1; restAmount <= amount; restAmount++) {
        int min = Integer.MAX_VALUE;
        int p1 = dp[index + 1][restAmount];
        if (p1 != -1) {
          min = Math.min(min, p1);
        }
        if (restAmount >= coins[index]) {
          int p2 = dp[index][restAmount - coins[index]];
          if (p2 != -1) {
            min = Math.min(min, 1 + p2);
          }
        }
        dp[index][restAmount] = (min == Integer.MAX_VALUE ? -1 : min);
      }
    }
    return dp[0][amount];
  }

  public static int coinChange4(int[] coins, int amount) {
    int n = coins.length;
    int[] dp = new int[amount + 1];
    // 从下到上，从右到左
    for (int col = amount; col > 0; col--) {
      dp[col] = -1;
    }
    dp[0] = 0;

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = coins[index]; restAmount <= amount; restAmount++) {
        int min = Integer.MAX_VALUE;
        if (dp[restAmount] != -1) {
          min = Math.min(min, dp[restAmount]);
        }
        int p2 = dp[restAmount - coins[index]];
        if (p2 != -1) {
          min = Math.min(min, 1 + p2);
        }
        dp[restAmount] = (min == Integer.MAX_VALUE ? -1 : min);
      }
    }
    return dp[amount];
  }

  public static int coinChange5(int[] coins, int amount) {
    int n = coins.length;
    int[] dp = new int[amount + 1];
    // 从下到上，从右到左
    for (int col = amount; col > 0; col--) {
      dp[col] = amount + 1;
    }
    dp[0] = 0;

    for (int index = n - 1; index >= 0; index--) {
      for (int restAmount = coins[index]; restAmount <= amount; restAmount++) {
        dp[restAmount] = Math.min(dp[restAmount], dp[restAmount - coins[index]] + 1);
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    int[] coins = {1, 2, 5};
    int amount = 11;
    System.out.println(coinChange5(coins, amount));
  }
}
