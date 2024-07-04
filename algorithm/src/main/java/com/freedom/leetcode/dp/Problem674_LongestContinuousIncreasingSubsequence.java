package com.freedom.leetcode.dp;

/**
 * 674. Longest Continuous Increasing Subsequence
 *
 * @author tobebetter9527
 * @create 2022/12/10 12:54
 */
public class Problem674_LongestContinuousIncreasingSubsequence {

    /**
     * greedy
     * <p>
     * time complexity is O(n), space complexity is O(1)
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int maxLen = 1;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len++;
            } else {
                maxLen = Math.max(maxLen, len);
                len = 1;
            }
        }
        return Math.max(maxLen, len);
    }

    public static int findLengthOfLCIS2(int[] nums) {
        // dp[i], i位置结尾的最长子数组
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        System.out.println(findLengthOfLCIS2(nums));
    }


}
