package com.freedom.leetcode.array;

/**
 * 1365. How Many Numbers Are Smaller Than the Current Number
 *
 * @author tobebetter9527
 * @create 2022/12/15 21:03
 */
public class Problem1365_HowManyNumbersAreSmallerThantheCurrentNumber {


  public static int[] smallerNumbersThanCurrent(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      int res = 0;
      for (int j = 0; j < n; j++) {
        if (i != j && nums[i] > nums[j]) {
          res++;
        }
      }
      ans[i] = res;
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = {8, 1, 2, 2, 3};
    int[] ints = smallerNumbersThanCurrent(nums);
    for (int anInt : ints) {
      System.out.println(anInt);
    }

  }
}
