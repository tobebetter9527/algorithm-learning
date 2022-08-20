package com.freedom.zuo.class39_catalan_number;


import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非负数组arr，和一个正数m。
 * <p>
 * 返回arr的所有子序列中累加和%m之后的最大值。
 *
 * @author tobebetter9527
 * @create 2022/08/20 9:38
 */
public class Code01_SubsquenceMaxModM {

  /**
   * 暴力递归
   *
   * @param arr 数组
   * @param m   模数
   */
  public static int max1(int[] arr, int m) {
    Set<Integer> set = new HashSet<>();
    process1(arr, 0, 0, set);
    int max = 0;
    for (Integer sum : set) {
      max = Math.max(max, sum % m);
    }
    return max;
  }

  private static void process1(int[] arr, int index, int sum, Set<Integer> set) {
    if (index == arr.length) {
      set.add(sum);
    } else {
      // 不取当前数
      process1(arr, index + 1, sum, set);
      // 取当前数
      process1(arr, index + 1, sum + arr[index], set);
    }
  }

  // ----------------------------------------------- //

  /**
   * 动态规划
   * <p>
   * 如果sum数据量比较小，用这个解法
   *
   * @param arr 数组
   * @param m   模数
   */
  public static int max2(int[] arr, int m) {
    int sum = 0;
    for (int i : arr) {
      sum += i;
    }
    int n = arr.length;
    // 表示0~i的数字自由选择，能不能组成sum数字
    boolean[][] dp = new boolean[n][sum + 1];
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }
    dp[0][arr[0]] = true;

    for (int index = 1; index < n; index++) {
      for (int j = 1; j <= sum; j++) {
        // 不取当前数
        dp[index][j] = dp[index - 1][j];
        // 取当前数
        if (j - arr[index] >= 0) {
          dp[index][j] |= dp[index - 1][j - arr[index]];
        }
      }
    }

    int ans = 0;
    for (int j = 0; j <= sum; j++) {
      if (dp[n - 1][j]) {
        ans = Math.max(ans, j % m);
      }
    }

    return ans;
  }

  // --------------------------------------------- //

  public static void main(String[] args) {
    int maxLength = 100;
    int maxValue = 100;
    int testTimes = 1000000;
    int m = 76;
    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateRandomArray(maxLength, maxValue);
      int ans1 = max1(arr, m);
      int ans2 = max2(arr, m);
      if (ans1 != ans2) {
        System.out.println("wrong");
      }

    }
  }

  private static int[] generateRandomArray(int maxLength, int maxValue) {
    int length = (int) (Math.random() * maxLength);
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = (int) (Math.random() * maxValue);
    }
    return arr;
  }


}
