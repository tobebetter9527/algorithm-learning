package com.freedom.zuo.class41_quadrangle_inequality;

/**
 * 摆放着n堆石子。现要将石子有次序地合并成一堆
 * <p>
 * 规定每次只能选相邻的2堆石子合并成新的一堆，
 * <p>
 * 并将新的一堆石子数记为该次合并的得分
 * <p>
 * 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 *
 * @author tobebetter9527
 * @create 2022/08/24 20:45
 */
public class Code03_StoneMerge {

  /**
   * 暴力递归
   *
   * @param arr
   */
  public static int min1(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }

    int[] sums = sum(arr);
    return process1(sums, 0, arr.length - 1);
  }

  /**
   * 求0~0，1~right的累加值，求0~1，2~right的累加值，以此类推；求累加值的最小值
   *
   * @param sums
   * @param left
   * @param right
   */
  private static int process1(int[] sums, int left, int right) {
    if (left == right) {
      return 0;
    }
    int next = Integer.MAX_VALUE;
    for (int leftEnd = left; leftEnd < right; leftEnd++) {
      next = Math.min(next, process1(sums, left, leftEnd) + process1(sums, left + 1, right));
    }
    // 假设数组{1，2，6，3），假设（1，2），（6，3）分别合并得到两个数，然后再合并，所得的分数最小，
    // 分数计算： 3 + 6 + （3 + 6）,所以这里： substract(sums, left, right)
    return next + substract(sums, left, right);
  }


  private static int[] sum(int[] arr) {
    int[] sums = new int[arr.length + 1];
    for (int i = 0; i < arr.length; i++) {
      sums[i + 1] = sums[i] + arr[i];
    }
    return sums;
  }

  private static int substract(int[] sums, int left, int right) {
    return sums[right + 1] - sums[left];
  }

}
