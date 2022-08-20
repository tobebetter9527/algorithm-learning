package com.freedom.zuo.class39_catalan_number;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
  public static int dp1(int[] arr, int m) {
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


  /**
   * 递归
   * <p>
   * 如果sum数据量比较很大，m很小，用此解法
   *
   * @param arr 数组
   * @param m   模数
   */
  public static int max2(int[] arr, int m) {
    int index = arr.length - 1;
    for (int i = m - 1; i > 0; i--) {
      if (process2(arr, index, i, m)) {
        return i;
      }
    }
    return 0;
  }

  /**
   * 经过index，累加和求余数严格为restM
   *
   * @param arr
   * @param index
   * @param targetM
   * @param m
   */
  private static boolean process2(int[] arr, int index, int targetM, int m) {
    if (index == 0) {
      // 特别注意targetM等于0
      return targetM == 0 || arr[index] % m == targetM;
    }

    // index-1 索引位置数字不要
    boolean flag1 = process2(arr, index - 1, targetM, m);

    // index-1 索引位置数字要
    boolean flag2;
    int restNum = arr[index] % m;
    if (restNum <= targetM) {
      flag2 = process2(arr, index - 1, targetM - restNum, m);
    } else {
      flag2 = process2(arr, index - 1, m + targetM - restNum, m);
    }

    return flag1 || flag2;
  }


  /**
   * 动态规划
   * <p>
   * 如果sum数据量比较很大，m很小，用此解法
   *
   * @param arr 数组
   * @param m   模数
   */
  public static int dp2(int[] arr, int m) {
    boolean[][] dp = new boolean[arr.length][m];
    for (int i = 0; i < arr.length; i++) {
      dp[i][0] = true;
    }
    // 终止条件
    dp[0][arr[0] % m] = true;

    for (int index = 1; index < arr.length; index++) {
      for (int targetM = 0; targetM < m; targetM++) {
        // index-1 索引位置数字不要
        dp[index][targetM] = dp[index - 1][targetM];

        // index-1 索引位置数字要
        int restNum = arr[index] % m;
        if (restNum <= targetM) {
          dp[index][targetM] |= dp[index - 1][targetM - restNum];
        } else {
          dp[index][targetM] |= dp[index - 1][m + targetM - restNum];
        }
      }
    }

    for (int i = m - 1; i > 0; i--) {
      if (dp[arr.length - 1][i]) {
        return i;
      }
    }

    return 0;
  }

  // --------------------------------------------- //

  // 如果arr的累加和很大，m也很大
  // 但是arr的长度相对不大
  public static int max4(int[] arr, int m) {
    if (arr.length == 1) {
      return arr[0] % m;
    }
    int mid = (arr.length - 1) / 2;
    TreeSet<Integer> sortSet1 = new TreeSet<>();
    process4(arr, 0, 0, mid, m, sortSet1);
    TreeSet<Integer> sortSet2 = new TreeSet<>();
    process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
    int ans = 0;
    for (Integer leftMod : sortSet1) {
      ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
    }
    return ans;
  }

  // 从index出发，最后有边界是end+1，arr[index...end]
  public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
    if (index == end + 1) {
      sortSet.add(sum % m);
    } else {
      process4(arr, index + 1, sum, end, m, sortSet);
      process4(arr, index + 1, sum + arr[index], end, m, sortSet);
    }
  }


  // --------------------------------------------- //

  public static void main(String[] args) {
    int maxLength = 10;
    int maxValue = 100;
    int testTimes = 1000000;

    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateRandomArray(maxLength, maxValue);
      int m = (int) (Math.random() * maxValue) + 1;
      int ans1 = max1(arr, m);
      int ans2 = dp1(arr, m);
      int ans3 = max2(arr, m);
      int ans4 = dp2(arr, m);
      if (ans1 != ans2 && ans1 != ans3 && ans1 != ans4) {
        System.out.println("wrong");
      }
    }
    System.out.println("done!");
  }

  private static int[] generateRandomArray(int maxLength, int maxValue) {
    int length = (int) (Math.random() * maxLength) + 1;
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = (int) (Math.random() * maxValue);
    }
    return arr;
  }


}
