package com.freedom.zuo.class21_dynamic_programming4;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * <p>
 * 每个值都认为是一张货币，
 * <p>
 * 认为值相同的货币没有任何不同，
 * <p>
 * 返回组成aim的方法数
 * <p>
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * <p>
 * 方法：1+1+1+1、1+1+2、2+2
 * <p>
 * 一共就3种方法，所以返回3
 *
 * @author tobebetter9527
 * @create 2022/07/20 20:54
 */
public class Code04_CoinsWaySameValueSamePapper {

  /**
   * 暴力递归
   *
   * @param arr 货币数组
   * @param aim 目标值
   * @return
   */
  public static int coinsWay(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }

    Info info = new Info(arr).invoke();

    return process(info.getCoins(), info.getCounts(), 0, aim);
  }

  /**
   * 与上提不同在于货币数量有限制
   *
   * @param coins
   * @param counts
   * @param index
   * @param restAim
   * @return
   */
  private static int process(int[] coins, int[] counts, int index, int restAim) {
    if (index == coins.length) {
      return restAim == 0 ? 1 : 0;
    }
    int ways = 0;
    for (int count = 0; count <= counts[index] && count * coins[index] <= restAim; count++) {
      ways += process(coins, counts, index + 1, restAim - count * coins[index]);
    }
    return ways;
  }

  // ------------------------------------------ //

  /**
   * 动态规划
   *
   * @param arr 货币数组
   * @param aim 目标值
   * @return
   */
  public static int coinsWay2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }

    Info info = new Info(arr).invoke();
    int[] coins = info.getCoins();
    int[] counts = info.getCounts();
    int length = counts.length;

    int[][] dp = new int[length + 1][aim + 1];
    dp[length][0] = 1;

    // 从下到上，从左到右
    for (int index = length - 1; index >= 0; index--) {
      for (int restAim = 0; restAim <= aim; restAim++) {
        int ways = 0;
        for (int count = 0; count <= counts[index] && count * coins[index] <= restAim; count++) {
          ways += dp[index + 1][restAim - count * coins[index]];
        }
        dp[index][restAim] = ways;
      }
    }

    return dp[0][aim];
  }

  // ------------------------------------------ //

  /**
   * 动态规划,优化
   *
   * @param arr 货币数组
   * @param aim 目标值
   * @return
   */
  public static int coinsWay3(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }

    Info info = new Info(arr).invoke();
    int[] coins = info.getCoins();
    int[] counts = info.getCounts();
    int length = counts.length;

    int[][] dp = new int[length + 1][aim + 1];
    dp[length][0] = 1;

    // 从下到上，从左到右
    for (int index = length - 1; index >= 0; index--) {
      for (int restAim = 0; restAim <= aim; restAim++) {
        dp[index][restAim] = dp[index + 1][restAim];
        if (restAim - coins[index] >= 0) {
          dp[index][restAim] += dp[index][restAim - coins[index]];
        }

        // 特殊之处，货币的张数有限制
        if (restAim - coins[index] * (counts[index] + 1) >= 0) {
          dp[index][restAim] -= dp[index + 1][restAim - coins[index] * (counts[index] + 1)];
        }
      }
    }

    return dp[0][aim];
  }

  // ------------------------------------------ //

  // 为了测试
  public static int[] randomArray(int maxLen, int maxValue) {
    int N = (int) (Math.random() * maxLen);
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = (int) (Math.random() * maxValue) + 1;
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
    int maxLen = 10;
    int maxValue = 20;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int[] arr = randomArray(maxLen, maxValue);
      int aim = (int) (Math.random() * maxValue);
      int ans1 = coinsWay(arr, aim);
      int ans2 = coinsWay2(arr, aim);
      int ans3 = coinsWay3(arr, aim);
      if (ans1 != ans2 || ans1 != ans3) {
        System.out.println("Oops!");
        printArray(arr);
        System.out.println(aim);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        break;
      }
    }
    System.out.println("测试结束");
  }


  private static class Info {

    private int[] arr;
    private int[] coins;
    private int[] counts;

    public Info(int... arr) {
      this.arr = arr;
    }

    public int[] getCoins() {
      return coins;
    }

    public int[] getCounts() {
      return counts;
    }

    public Info invoke() {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i : arr) {
        map.put(i, map.getOrDefault(i, 0) + 1);
      }
      int size = map.size();
      coins = new int[size];
      counts = new int[size];
      int index = 0;
      for (Entry<Integer, Integer> entry : map.entrySet()) {
        coins[index] = entry.getKey();
        counts[index++] = entry.getValue();
      }
      return this;
    }
  }
}
