package com.freedom.leetcode.dp;

/**
 * 1035. Uncrossed Lines
 */
public class Problem1035_UncrossedLines {

  /**
   * 暴力递归
   * @param nums1
   * @param nums2
   * @return
   */
  public int maxUncrossedLines(int[] nums1, int[] nums2) {
    return recursive(nums1, nums2, 0, 0);
  }

  private int recursive(int[] nums1, int[] nums2, int index1, int index2) {
    if (index1 == nums1.length || index2 == nums2.length) {
      return 0;
    }
    // 不取index1
    int p1 = recursive(nums1, nums2, index1 + 1, index2);

    // 取index1
    int p2 = 0;
    if (nums1[index1] == nums2[index2]) {
      p2 = 1 + recursive(nums1, nums2, index1 + 1, index2 + 1);
    } else {
      p2 = recursive(nums1, nums2, index1, index2 + 1);
    }

    return Math.max(p1, p2);
  }

  /**
   * time complexity is O(n*m),space complexity is O(n*M).
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public int maxUncrossedLines2(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int[][] dp = new int[n + 1][m + 1];

    // 从下到上，从右到左
    for (int index1 = n - 1; index1 >= 0; index1--) {
      for (int index2 = m - 1; index2 >= 0; index2--) {
        // 不取index1
        int p1 = dp[index1 + 1][index2];

        // 取index1
        int p2 = 0;
        if (nums1[index1] == nums2[index2]) {
          p2 = 1 + dp[index1 + 1][index2 + 1];
        } else {
          p2 = dp[index1][index2 + 1];
        }

        dp[index1][index2] = Math.max(p1, p2);
      }
    }
    return dp[0][0];
  }


}
