package com.freedom.zuo.class41_quadrangle_inequality;

/**
 * 把题目一中提到的， min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
 * <p>
 * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * <p>
 * 现在要求返回一个长度为N的s数组，
 * <p>
 * s[i] =在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * <p>
 * 得到整个s数组的过程，做到时间复杂度O(N)
 */
public class Code02_BestSplitForEveryPosition {

  /**
   * 暴力计算
   *
   * @param arr
   */
  public static int[] bestSplit1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }

    int length = arr.length;
    int[] ans = new int[length];
    ans[0] = 0;

    for (int range = 1; range < length; range++) {
      for (int s = 0; s < range; s++) {
        int sumL = 0;
        for (int l = 0; l <= s; l++) {
          sumL += arr[l];
        }

        int sumR = 0;
        for (int r = s + 1; r <= range; r++) {
          sumR += arr[r];
        }

        ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
      }
    }
    return ans;
  }

  // ------------------------------- //

  /**
   * 暴力计算的优化
   *
   * @param arr
   */
  public static int[] bestSplit2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }

    int length = arr.length;
    int[] ans = new int[length];
    ans[0] = 0;

    // 增加一位，减少判断
    int[] sums = new int[length + 1];
    for (int i = 0; i < length; i++) {
      sums[i + 1] = sums[i] + arr[i];
    }

    for (int range = 1; range < length; range++) {
      for (int s = 0; s < range; s++) {
        int sumL = sum(sums, 0, s);
        int sumR = sum(sums, s + 1, range);
        ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
      }
    }

    return ans;
  }

  // ------------------------------- //

  private static int sum(int[] sums, int left, int right) {
    return sums[right + 1] - sums[left];
  }

  /**
   * 最优解
   *
   * @param arr
   * @return
   */
  public static int[] bestSplit3(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }

    int length = arr.length;
    int[] ans = new int[length];
    ans[0] = 0;

    // 增加一位，减少判断
    int[] sums = new int[length + 1];
    for (int i = 0; i < length; i++) {
      sums[i + 1] = sums[i] + arr[i];
    }

    int best = 0;
    for (int range = 1; range < length; range++) {
      while (best + 1 < range) {
        int before = Math.min(sum(sums, 0, best), sum(sums, best + 1, range));
        int after = Math.min(sum(sums, 0, best + 1), sum(sums, best + 2, range));
        if (before <= after) {
          best++;
        } else {
          break;
        }
      }
      ans[range] = Math.max(ans[range], Math.min(sum(sums, 0, best), sum(sums, best + 1, range)));
    }

    return ans;
  }

  // ------------------------------- //

  public static int[] randomArray(int len, int max) {
    int[] ans = new int[len];
    for (int i = 0; i < len; i++) {
      ans[i] = (int) (Math.random() * max);
    }
    return ans;
  }

  public static boolean isSameArray(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }
    int N = arr1.length;
    for (int i = 0; i < N; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int N = 20;
    int max = 30;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * N);
      int[] arr = randomArray(len, max);
      int[] ans1 = bestSplit1(arr);
      int[] ans2 = bestSplit2(arr);
      int[] ans3 = bestSplit3(arr);
      if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("测试结束");
  }
}
