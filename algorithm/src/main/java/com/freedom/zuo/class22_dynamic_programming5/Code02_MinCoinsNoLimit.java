package com.freedom.zuo.class22_dynamic_programming5;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * <p>
 * 每个值都认为是一种面值，且认为张数是无限的。
 * <p>
 * 返回组成aim的最少货币数
 *
 * @author tobebetter9527
 * @create 2022/07/22 21:00
 */
public class Code02_MinCoinsNoLimit {

  /**
   * 暴力迭代
   *
   * @param arr 货币数组，每个面值有无限张
   * @param aim 目标值
   * @return 返回组成aim的最少货币数
   */
  public static int minCoins(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 1) {
      return 0;
    }
    return process(arr, 0, aim);
  }

  /**
   * @param arr     货币数组，每个面值有无限张
   * @param index   当来用到第几个索引位置的货币
   * @param restAim 剩余目标值
   */
  private static int process(int[] arr, int index, int restAim) {
    // 当前index已经超出数组大小,如果restAim为0，表示不需要货币了，不为了，说明找不到货币值凑出当前的restAim。
    if (index == arr.length) {
      return restAim == 0 ? 0 : Integer.MAX_VALUE;
    }

    int ans = Integer.MAX_VALUE;
    for (int count = 0; count * arr[index] <= restAim; count++) {
      int next = process(arr, index + 1, restAim - count * arr[index]);
      if (next != Integer.MAX_VALUE) {
        ans = Math.min(ans, count + next);
      }
    }

    return ans;
  }

  // -------------------------------------------------------------------------- //

  /**
   * 动态规划1
   *
   * @param arr 货币数组，每个面值有无限张
   * @param aim 目标值
   * @return 返回组成aim的最少货币数
   */
  public static int dp1(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 1) {
      return 0;
    }

    int n = arr.length;
    int[][] dp = new int[n + 1][aim + 1];

    // 当前index已经超出数组大小,如果restAim为0，表示不需要货币了，不为了，说明找不到货币值凑出当前的restAim。
    dp[n][0] = 0;
    for (int restAim = 1; restAim <= aim; restAim++) {
      dp[n][restAim] = Integer.MAX_VALUE;
    }

    // 从下到上，从小到大
    for (int index = n - 1; index >= 0; index--) {
      dp[index][0] = dp[index + 1][0];

      for (int restAim = 1; restAim <= aim; restAim++) {
        int ans = Integer.MAX_VALUE;
        for (int count = 0; count * arr[index] <= restAim; count++) {
          int next = dp[index + 1][restAim - count * arr[index]];
          if (next != Integer.MAX_VALUE) {
            ans = Math.min(ans, count + next);
          }
        }
        dp[index][restAim] = ans;
      }
    }

    return dp[0][aim];
  }

  // -------------------------------------------------------------------------- //

  /**
   * 动态规划2,优化版本
   *
   * @param arr 货币数组，每个面值有无限张
   * @param aim 目标值
   * @return 返回组成aim的最少货币数
   */
  public static int dp2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 1) {
      return 0;
    }

    int n = arr.length;
    int[][] dp = new int[n + 1][aim + 1];

    // 当前index已经超出数组大小,如果restAim为0，表示不需要货币了，不为了，说明找不到货币值凑出当前的restAim。
    dp[n][0] = 0;
    for (int restAim = 1; restAim <= aim; restAim++) {
      dp[n][restAim] = Integer.MAX_VALUE;
    }

    // 从下到上，从小到大
    for (int index = n - 1; index >= 0; index--) {
      dp[index][0] = dp[index + 1][0];

      for (int restAim = 1; restAim <= aim; restAim++) {
        // 目标值底下的值
        dp[index][restAim] = dp[index + 1][restAim];
        // 跟左边的一个相比，特别注意dp[index][restAim - arr[index]] != Integer.MAX_VALUE
        if (restAim - arr[index] >= 0 && dp[index][restAim - arr[index]] != Integer.MAX_VALUE) {
          dp[index][restAim] = Math.min(dp[index][restAim], dp[index][restAim - arr[index]] + 1);
        }
      }
    }

    return dp[0][aim];
  }

  // -------------------------------------------------------------------------- //

  // 为了测试
  public static int[] randomArray(int maxLen, int maxValue) {
    int N = (int) (Math.random() * maxLen);
    int[] arr = new int[N];
    boolean[] has = new boolean[maxValue + 1];
    for (int i = 0; i < N; i++) {
      do {
        arr[i] = (int) (Math.random() * maxValue) + 1;
      } while (has[arr[i]]);
      has[arr[i]] = true;
    }
    return arr;
  }

  // 为了测试
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // 为了测试
  public static void main(String[] args) {
    int maxLen = 20;
    int maxValue = 30;
    int testTime = 300000;
    System.out.println("功能测试开始");
    for (int i = 0; i < testTime; i++) {
      int N = (int) (Math.random() * maxLen);
      int[] arr = randomArray(N, maxValue);
      int aim = (int) (Math.random() * maxValue);
      int ans1 = minCoins(arr, aim);
      int ans2 = dp1(arr, aim);
      int ans3 = dp2(arr, aim);
      if (ans1 != ans2 || ans1 != ans3) {
        System.out.println("Oops!");
        printArray(arr);
        System.out.println(aim);
        System.out.println(ans1);
        System.out.println(ans2);
        break;
      }
    }
    System.out.println("功能测试结束");
  }

}
