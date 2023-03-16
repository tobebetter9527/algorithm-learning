package com.freedom.sword_offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tobebetter9527
 * @create 2023/03/04 9:42
 */
public class Offer57 {

  public static int[] twoSum(int[] nums, int target) {
    if (nums.length <= 1) {
      return new int[0];
    }

    int i = 0, j = nums.length - 1;
    while (i < j) {
      int s = nums[i] + nums[j];
      if (s > target) {
        j--;
      } else if (s < target) {
        i++;
      } else {
        return new int[]{nums[i], nums[j]};
      }
    }
    return new int[0];
  }


  public static int[] twoSum1(int[] nums, int target) {
    if (nums.length <= 1) {
      return new int[0];
    }
    int[] ans = new int[2];
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      int temp = target - nums[i];
      int idx = -1;
      if (temp > nums[i]) {
        idx = Arrays.binarySearch(nums, i + 1, len - 1, temp);
      } else {
        idx = Arrays.binarySearch(nums, 0, i - 1, temp);
      }
      if (idx > -1) {
        ans[0] = nums[i];
        ans[1] = nums[idx];
        return ans;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = {16, 16, 18, 24, 30, 32};
    int target = 48;
    System.out.println(twoSum(nums, target));
  }

  public int[] twoSum2(int[] nums, int target) {
    if (nums.length <= 1) {
      return new int[0];
    }
    int[] ans = new int[2];
    Set<Integer> set = new HashSet<>(nums.length);
    for (int num : nums) {
      int temp = target - num;
      if (set.contains(temp)) {
        ans[0] = num;
        ans[1] = temp;
        return ans;
      } else {
        set.add(num);
      }
    }
    return ans;
  }
}
