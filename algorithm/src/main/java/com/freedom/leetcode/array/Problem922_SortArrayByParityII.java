package com.freedom.leetcode.array;


/**
 * 922. Sort Array By Parity II
 *
 * @author tobebetter9527
 * @create 2022/12/17 11:23
 */
public class Problem922_SortArrayByParityII {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param nums
   * @return
   */
  public static int[] sortArrayByParityII(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    int evenIndex = 0;
    int oddIndex = 1;
    for (int i = 0; i < n; i++) {
      if ((nums[i] & 1) == 1) {
        ans[oddIndex] = nums[i];
        oddIndex += 2;
      } else {
        ans[evenIndex] = nums[i];
        evenIndex += 2;
      }
    }
    return ans;
  }

  public static int[] sortArrayByParityII2(int[] nums) {
    int n = nums.length;
    int oddIndex = 1;
    for (int evenIndex = 0; evenIndex < n; evenIndex += 2) {
      if ((nums[evenIndex] & 1) == 1) {
        while ((nums[oddIndex] & 1) == 1) {
          oddIndex += 2;
        }
        swap(nums, evenIndex, oddIndex);
      }
    }
    return nums;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {4, 2, 5, 7};
    int[] ints = sortArrayByParityII(nums);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}
