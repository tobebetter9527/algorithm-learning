package com.freedom.zuo.class23_dynamic_programming6;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，
 * <p>
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * <p>
 * 返回： 最接近的情况下，较小集合的累加和
 *
 * @author tobebetter9527
 * @create 2022/07/23 15:13
 */
public class Code01_SplitSumClosed {

  /**
   * 暴力递归
   *
   * @param arr 数组
   * @return 最接近的情况下，较小集合的累加和
   */
  public static int right(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int sum = Arrays.stream(arr).sum() >> 1;

    return process(arr, 0, sum);
  }

  /**
   * @param arr     数组
   * @param index   当前取数位置
   * @param restSum 剩余累加和
   */
  private static int process(int[] arr, int index, int restSum) {
    if (index == arr.length) {
      return 0;
    }
    // 不要当前位置的值
    int p1 = process(arr, index + 1, restSum);

    // 要当前位置的值
    int p2 = 0;
    if (arr[index] <= restSum) {
      p2 = arr[index] + process(arr, index + 1, restSum - arr[index]);
    }

    return Math.max(p1, p2);
  }

  // ------------------------------------------------------  //

  /**
   * 动态规划
   *
   * @param arr 数组
   * @return 最接近的情况下，较小集合的累加和
   */
  public static int dp1(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int sum = Arrays.stream(arr).sum() >> 1;
    int n = arr.length;
    int[][] dp = new int[n + 1][sum + 1];

    // 从下到上，从左到右
    for (int index = n - 1; index >= 0; index--) {
      for (int restSum = 0; restSum <= sum; restSum++) {
        // 不要当前位置的值
        int p1 = dp[index + 1][restSum];

        // 要当前位置的值
        int p2 = 0;
        if (arr[index] <= restSum) {
          p2 = arr[index] + dp[index + 1][restSum - arr[index]];
        }

        dp[index][restSum] = Math.max(p1, p2);
      }
    }

    return dp[0][sum];
  }

  // ------------------------------------------------------  //

  public static void main(String[] args) {
    int testTimes = 100000;
    int maxLength = 10;
    int maxValue = 20;

    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateArray(maxLength, maxValue);
      int right = right(arr);
      int value = dp1(arr);
      if (right != value) {
        System.out.println(right);
      }
    }
    System.out.println("done!");
  }

  private static int[] generateArray(int maxLength, int maxValue) {
    int length = (int) (Math.random() * maxLength);
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = (int) (Math.random() * maxValue);
    }
    return arr;
  }


}
