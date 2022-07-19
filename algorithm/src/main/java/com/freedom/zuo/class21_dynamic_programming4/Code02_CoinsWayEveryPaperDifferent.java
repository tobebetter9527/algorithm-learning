package com.freedom.zuo.class21_dynamic_programming4;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * <p>
 * 每个值都认为是一张货币，
 * <p>
 * 即便是值相同的货币也认为每一张都是不同的，
 * <p>
 * 返回组成aim的方法数
 * <p>
 * 例如：arr = {1,1,1}，aim = 2
 * <p>
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * <p>
 * 一共就3种方法，所以返回3
 *
 * @author tobebetter9527
 * @create 2022/07/19 20:26
 */
public class Code02_CoinsWayEveryPaperDifferent {

  /**
   * 暴力递归
   *
   * @param arr 货币数组
   * @param aim 目标值
   * @return
   */
  public static int coinWays(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }

    return process(arr, 0, aim);
  }

  /**
   * @param arr     货币数组
   * @param index   当前index位置
   * @param restAim 剩余目标值
   * @return
   */
  private static int process(int[] arr, int index, int restAim) {
    if (restAim < 0) {
      return 0;
    }
    if (index == arr.length) {
      return restAim == 0 ? 1 : 0;
    } else {
      // 当前位置不取和取两种做法
      return process(arr, index + 1, restAim) + process(arr, index + 1, restAim - arr[index]);
    }
  }


  /**
   * 动态规划
   *
   * @param arr 货币数组
   * @param aim 目标值
   * @return
   */
  public static int coinWays2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    int length = arr.length;
    int[][] dp = new int[arr.length + 1][aim + 1];
    dp[length][0] = 1;

    // 从下到上，从左到右（从右到左也行）
    for (int i = length - 1; i >= 0; i--) {
      for (int j = 0; j <= aim; j++) {
        dp[i][j] = dp[i + 1][j] + (j - arr[i] < 0 ? 0 : dp[i + 1][j - arr[i]]);
      }
    }

    return dp[0][aim];
  }


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
    int maxLen = 20;
    int maxValue = 30;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int[] arr = randomArray(maxLen, maxValue);
      int aim = (int) (Math.random() * maxValue);
      int ans1 = coinWays(arr, aim);
      int ans2 = coinWays2(arr, aim);
      if (ans1 != ans2) {
        System.out.println("Oops!");
        printArray(arr);
        System.out.println(aim);
        System.out.println(ans1);
        System.out.println(ans2);
        break;
      }
    }
    System.out.println("测试结束");
  }

}
