package com.freedom.leetcode.dp;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 *
 * @author tobebetter9527
 * @create 2022/12/03 19:56
 */
public class Problem416_PartitionEqualSubsetSum {

  public static boolean canPartition(int[] nums) {
    if (nums == null || nums.length < 2) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();
    // 奇数肯定是不能平分的
    if ((sum & 1) == 1) {
      return false;
    }
    // 取一半
    int halfSum = sum >> 1;
    return recursive(nums, 0, halfSum);
  }

  private static boolean recursive(int[] nums, int index, int restSum) {
    // 剩余的和为0，肯定是符合条件
    if (restSum == 0) {
      return true;
    }
    if (restSum < 0) {
      return false;
    }
    // index已经超过length，restSum还没为0，肯定是不行的
    if (index == nums.length) {
      return false;
    }

    // 取当前的值
    boolean flag1 = recursive(nums, index + 1, restSum - nums[index]);
    // 不取当前的值
    boolean flag2 = recursive(nums, index + 1, restSum);

    return flag1 || flag2;
  }

  /**
   * time complexity is O(n*halfsum), space complexity is O(n*halfsum)
   *
   * @param nums
   * @return
   */
  public static boolean canPartition2(int[] nums) {
    if (nums == null || nums.length < 2) {
      return false;
    }

    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    // 奇数肯定是不能平分的
    if ((sum & 1) == 1) {
      return false;
    }
    // 取一半
    int halfSum = sum >> 1;
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][halfSum + 1];

    // 从下到上，从左到右
    //    for (int restSum = 1; restSum <= halfSum ; restSum++) {
    //      dp[n][restSum] = false;
    //    }
    for (int index = n; index >= 0; index--) {
      dp[index][0] = true;
    }

    for (int index = n - 1; index >= 0; index--) {
      for (int restSum = 1; restSum <= halfSum; restSum++) {
        // 取当前的值
        if (restSum - nums[index] >= 0) {
          dp[index][restSum] = dp[index + 1][restSum - nums[index]];
        }
        // 不取当前的值
        dp[index][restSum] |= dp[index + 1][restSum];
      }
    }

    return dp[0][halfSum];
  }

  /**
   * time complexity is O(n*halfsum), space complexity is O(halfsum)
   *
   * @param nums
   * @return
   */
  public static boolean canPartition3(int[] nums) {
    if (nums == null || nums.length < 2) {
      return false;
    }

    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    // 奇数肯定是不能平分的
    if ((sum & 1) == 1) {
      return false;
    }
    // 取一半
    int halfSum = sum >> 1;
    int n = nums.length;
    // 压缩空间
    boolean[] dp = new boolean[halfSum + 1];
    dp[0] = true;

    // 错误写法
    //    for (int index = n - 1; index >= 0; index--) {
    //      for (int restSum = 1; restSum <= halfSum; restSum++) {
    //        // 取当前的值
    //        if (restSum - nums[index] >= 0) {
    //          dp[restSum] |= dp[restSum - nums[index]];
    //        }
    //        // 不取当前的值
    //        dp[restSum] |= dp[restSum];
    //      }
    //    }

    for (int index = n - 1; index >= 0; index--) {
      int num = nums[index];
      // 这样写错误，不是上一行的状态
      //      for (int restSum = num; restSum <= halfSum; restSum++) {
      //        // 取当前的值
      //        dp[restSum] |= dp[restSum - num];
      //      }
      for (int restSum = halfSum; restSum >= num; restSum--) {
        // 取当前的值
        dp[restSum] |= dp[restSum - num];
      }
    }

    return dp[halfSum];
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 5};
    System.out.println(canPartition3(nums));
  }
}
