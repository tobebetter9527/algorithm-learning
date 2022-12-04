package com.freedom.leetcode.dp;

/**
 * 1049. Last Stone Weight II
 *
 * @author tobebetter9527
 * @create 2022/12/04 8:27
 */
public class Problem1049_LastStoneWeightII {

  public static int lastStoneWeightII(int[] stones) {
    if (stones.length < 2) {
      return stones[0];
    }

    int sum = 0, n = stones.length;
    for (int i = 0; i < n; i++) {
      sum += stones[i];
    }

    int target = sum >> 1;
    target = recursive(stones, 0, target);
    return sum - 2 * target;
  }

  private static int recursive(int[] stones, int index, int restTarget) {
    if (index == stones.length) {
      return 0;
    }

    // 不取当前值
    int p1 = recursive(stones, index + 1, restTarget);
    // 取当前值
    int p2 = 0;
    if (restTarget >= stones[index]) {
      p2 = stones[index] + recursive(stones, index + 1, restTarget - stones[index]);
    }
    return Math.max(p1, p2);
  }

  public static int lastStoneWeightII2(int[] stones) {
    if (stones.length < 2) {
      return stones[0];
    }

    int sum = 0, n = stones.length;
    for (int i = 0; i < n; i++) {
      sum += stones[i];
    }
    int target = sum >> 1;

    int[][] dp = new int[n + 1][target + 1];
    // 可以省略
    //    for (int col = 0; col < target + 1; col++) {
    //      dp[n][col] = 0;
    //    }

    // 从下到上，从右到左
    for (int index = n - 1; index >= 0; index--) {
      for (int restTarget = target ; restTarget >= 0; restTarget--) {
        // 不取当前值
        int p1 = dp[index + 1][restTarget];
        // 取当前值
        int p2 = 0;
        if (restTarget >= stones[index]) {
          p2 = stones[index] + dp[index + 1][restTarget - stones[index]];
        }
        dp[index][restTarget] = Math.max(p1, p2);
      }
    }

    return sum - 2 * dp[0][target];
  }

  public static int lastStoneWeightII3(int[] stones) {
    if (stones.length < 2) {
      return stones[0];
    }

    int sum = 0, n = stones.length;
    for (int i = 0; i < n; i++) {
      sum += stones[i];
    }
    int target = sum >> 1;

    int[]dp = new int[target + 1];
    // 从下到上，从右到左
    for (int index = n - 1; index >= 0; index--) {
      for (int restTarget = target ; restTarget >= stones[index]; restTarget--) {
        // 不取当前值
        int p1 = dp[restTarget];
        // 取当前值
        int p2 = stones[index] + dp[restTarget - stones[index]];
        dp[restTarget] = Math.max(p1, p2);
      }
    }

    return sum - 2 * dp[target];
  }


  public static void main(String[] args) {
    int[] stones = {2,7,4,1,8,1};
    System.out.println(lastStoneWeightII2(stones));

  }

}
