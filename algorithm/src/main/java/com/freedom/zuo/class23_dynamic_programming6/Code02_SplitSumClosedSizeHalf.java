package com.freedom.zuo.class23_dynamic_programming6;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * <p>
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * <p>
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * <p>
 * 请尽量让两个集合的累加和接近
 * <p>
 * 返回： 最接近的情况下，较小集合的累加和
 *
 * @author tobebetter9527
 * @create 2022/07/23 16:20
 */
public class Code02_SplitSumClosedSizeHalf {

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

    // 如果是偶数个
    if ((arr.length & 1) == 0) {
      return process(arr, 0, arr.length / 2, sum);
    } else {
      return Math.max(process(arr, 0, arr.length / 2, sum), process(arr, 0, arr.length / 2 + 1, sum));
    }
  }

  /**
   * @param arr       数组
   * @param index     当前索引位置
   * @param restPicks 剩余可以挑几个
   * @param restSum   剩余累加和
   */
  private static int process(int[] arr, int index, int restPicks, int restSum) {
    // 当前没得挑了
    if (index == arr.length) {
      // 如果restPicks还有值，说明无效，返回-1
      return restPicks == 0 ? 0 : -1;
    }

    // 当前位置不挑
    int p1 = process(arr, index + 1, restPicks, restSum);

    // 当前位置挑
    int p2 = -1;
    if (arr[index] <= restSum && (restPicks - 1) >= 0) {
      int next = process(arr, index + 1, restPicks - 1, restSum - arr[index]);
      if (next != -1) {
        p2 = arr[index] + next;
      }
    }

    return Math.max(p1, p2);
  }

  // --------------------------------------------------------//

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
    int picks = (n + 1) / 2;
    int[][][] dp = new int[n + 1][picks + 1][sum + 1];

    // 先所有的值默认为-1；
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= picks; j++) {
        for (int k = 0; k <= sum; k++) {
          dp[i][j][k] = -1;
        }
      }
    }

    // 根据递归终止条件
    for (int restSum = 0; restSum <= sum; restSum++) {
      dp[n][0][restSum] = 0;
    }

    // z轴从下到上，y轴从大到小，x轴从小到大
    for (int restPicks = 0; restPicks <= picks; restPicks++) {
      for (int index = n - 1; index >= 0; index--) {
        for (int restSum = 0; restSum <= sum; restSum++) {
          // 当前位置不挑
          int p1 = dp[index + 1][restPicks][restSum];

          // 当前位置挑
          int p2 = -1;
          if (arr[index] <= restSum && (restPicks - 1) >= 0) {
            int next = dp[index + 1][restPicks - 1][restSum - arr[index]];
            if (next != -1) {
              p2 = arr[index] + next;
            }
          }

          dp[index][restPicks][restSum] = Math.max(p1, p2);
        }
      }
    }

    // 如果是偶数个
    if ((arr.length & 1) == 0) {
      return dp[0][arr.length / 2][sum];
    } else {
      return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
    }
  }


  // ------------------------------------------------------//



  // ------------------------------------------------------//

  // for test
  public static int[] randomArray(int len, int value) {
    int[] arr = new int[len];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * value);
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  // for test
  public static void main(String[] args) {
    int maxLen = 10;
    int maxValue = 50;
    int testTime = 10000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * maxLen);
      int[] arr = randomArray(len, maxValue);
      int ans1 = right(arr);
      int ans2 = dp1(arr);
      //int ans3 = dp2(arr);
      if (ans1 != ans2 ) {
        printArray(arr);
        System.out.println(ans1);
        System.out.println(ans2);
      //  System.out.println(ans3);
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }



}
