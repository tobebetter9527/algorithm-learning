package com.freedom.leetcode.dp;

/**
 * 718. Maximum Length of Repeated Subarray
 *
 * @author tobebetter9527
 * @create 2022/12/10 17:20
 */
public class Problem718_MaximumLengthOfRepeatedSubarray {

    public static int findLength(int[] nums1, int[] nums2) {
        return recursive(nums1, nums2, nums1.length - 1, nums2.length - 1);
    }

    private static int recursive(int[] nums1, int[] nums2, int index1, int index2) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        }

        int maxLen = 0;
        int len = 0, index11 = index1, index22 = index2;
        while (index11 >= 0 && index22 >= 0 && nums1[index11] == nums2[index22]) {
            index11--;
            index22--;
            len++;
        }
        maxLen = Math.max(maxLen, len);

        int p1 = recursive(nums1, nums2, index1 - 1, index2);
        maxLen = Math.max(maxLen, p1);

        int p2 = recursive(nums1, nums2, index1, index2 - 1);
        maxLen = Math.max(maxLen, p2);

        return maxLen;
    }

    // 根据你的code改的动态规划，记忆化缓存版
    // 可以直接通过
    public static int findLength2(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                dp[i][j] = -1; // 用-1代表没算过
            }
        }
        return recursive2(nums1, nums2, nums1.length - 1, nums2.length - 1, dp);
    }

    private static int recursive2(int[] nums1, int[] nums2, int index1, int index2, int[][] dp) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        }
        if (dp[index1][index2] != -1) { // 不等于-1，说明算过，直接返回
            return dp[index1][index2];
        }
        int maxLen = 0;
        int len = 0, index11 = index1, index22 = index2;
        while (index11 >= 0 && index22 >= 0 && nums1[index11] == nums2[index22]) {
            index11--;
            index22--;
            len++;
        }
        maxLen = Math.max(maxLen, len);
        int p1 = recursive2(nums1, nums2, index1 - 1, index2, dp);
        maxLen = Math.max(maxLen, p1);
        int p2 = recursive2(nums1, nums2, index1, index2 - 1, dp);
        maxLen = Math.max(maxLen, p2);
        dp[index1][index2] = maxLen; // 加入缓存
        return maxLen;
    }

    // 根据你的code改的动态规划，严格位置依赖版
    // 可以直接通过
    public static int findLength3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = 0; i2 < m; i2++) {
                int maxLen = 0;
                int len = 0, i11 = i1, i22 = i2;
                while (i11 >= 0 && i22 >= 0 && nums1[i11] == nums2[i22]) {
                    i11--;
                    i22--;
                    len++;
                }
                maxLen = Math.max(maxLen, len);
                int p1 = i1 - 1 >= 0 ? dp[i1 - 1][i2] : 0;
                maxLen = Math.max(maxLen, p1);
                int p2 = i2 - 1 >= 0 ? dp[i1][i2 - 1] : 0;
                maxLen = Math.max(maxLen, p2);
                dp[i1][i2] = maxLen;
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        int[] nums2 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        System.out.println(findLength(nums1, nums2));
    }

}
