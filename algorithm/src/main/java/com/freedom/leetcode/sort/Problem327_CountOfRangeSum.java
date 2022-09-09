package com.freedom.leetcode.sort;

/**
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower,
 * upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 * @author tobebetter9527
 * @create 2022/06/13 22:13
 */
public class Problem327_CountOfRangeSum {

  /**
   * 前缀和，lower<= S(j) - S(i-1)<=upper, 转为S(j)-upper<=S(i-1)<=S(j)-lower
   *
   * 实质：考察以j位置结尾的子数组累加和问题。
   *
   * @param nums
   * @param lower
   * @param upper
   * @return
   */
  public static int countRangeSum(int[] nums, int lower, int upper) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    long[] sums = new long[nums.length];
    sums[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sums[i] = sums[i - 1] + nums[i];
    }

    return process(sums, 0, sums.length - 1, lower, upper);
  }

  private static int process(long[] sums, int left, int right, int lower, int upper) {
    if (left == right) {
      return (sums[left] >= lower && sums[right] <= upper) ? 1 : 0;
    }

    int mid = left + ((right - left) >> 1);
    return process(sums, left, mid, lower, upper) + process(sums, mid + 1, right, lower, upper) + merge(sums, left, mid,
        right, lower, upper);
  }

  private static int merge(long[] sums, int left, int mid, int right, int lower, int upper) {
    int ans = 0;
    int winL = left;
    int winR = left;
    for (int i = mid + 1; i <= right; i++) {
      long min = sums[i] - upper;
      long max = sums[i] - lower;
      while (winR <= mid && sums[winR] <= max) {
        winR++;
      }
      while (winL <= mid && sums[winL] < min) {
        winL++;
      }
      ans += winR - winL;
    }

    long[] help = new long[right - left + 1];
    int index1 = left;
    int index2 = mid + 1;
    int i = 0;
    while (index1 <= mid && index2 <= right) {
      help[i++] = sums[index1] <= sums[index2] ? sums[index1++] : sums[index2++];
    }
    while (index1 <= mid) {
      help[i++] = sums[index1++];
    }
    while (index2 <= right) {
      help[i++] = sums[index2++];
    }
    for (int j = 0; j < help.length; j++) {
      sums[left + j] = help[j];
    }

    return ans;
  }


  /**
   * 暴力迭代算个数
   *
   * @param nums
   * @param lower
   * @param upper
   * @return
   */
  public static int testCountRangeSum(int[] nums, int lower, int upper) {
    if (nums == null) {
      return 0;
    }
    int n = nums.length;

    long[] sums = new long[n];
    sums[0] = nums[0];
    for (int i = 1; i < n; i++) {
      sums[i] = sums[i - 1] + nums[i];
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        long value = sums[j] - (i - 1 < 0 ? 0 : sums[i - 1]);
        if (value >= lower && value <= upper) {
          ans++;
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    int rang = (1 << 31) - 1;
    int maxSize = 100000;
    int rangForLowerUpper = 100000;
    int testTimes = 100000000;
    for (int i = 0; i < testTimes; i++) {
      int[] nums = generateArray(rang, maxSize);
      int[] copyNums = copyArray(nums);
      int lower = (int) (Math.random() * rangForLowerUpper) - (int) (Math.random() * rangForLowerUpper);
      int upper = (int) (Math.random() * rangForLowerUpper) - (int) (Math.random() * rangForLowerUpper);
      if (lower > upper) {
        int temp = upper;
        upper = lower;
        lower = temp;
      }
      int testCountRangeSum = testCountRangeSum(copyNums, lower, upper);
      int countRangeSum = countRangeSum(nums, lower, upper);
      if (testCountRangeSum != countRangeSum) {
        System.out.println("testCountRangeSum=" + testCountRangeSum + ",countRangeSum=" + countRangeSum);
        print(nums);
      }
    }
  }

  private static void print(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("----");
  }

  private static int[] copyArray(int[] nums) {
    if (nums == null) {
      return null;
    }
    int[] copyNums = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      copyNums[i] = nums[i];
    }
    return copyNums;
  }

  private static int[] generateArray(int rang, int maxSize) {
    int length = (int) (Math.random() * (maxSize + 1));
    int[] nums = new int[length];
    for (int i = 0; i < length; i++) {
      nums[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
    }
    return nums;
  }


}
