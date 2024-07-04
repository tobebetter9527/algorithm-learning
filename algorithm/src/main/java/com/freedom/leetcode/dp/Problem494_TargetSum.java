package com.freedom.leetcode.dp;

/**
 * 494. Target Sum
 * <p>
 * 官方的dp解法更好
 *
 * @author tobebetter9527
 * @create 2022/12/04 10:04
 */
public class Problem494_TargetSum {

    /**
     * time complexity is O(2^n), space complexity O(n)
     * <p>
     * 数组的值均为非负整数
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        return recursive(nums, 0, target);
    }

    private static int recursive(int[] nums, int index, int restTarget) {
        // 全部设置完成
        if (index == nums.length) {
            // 目标值为0，则找到一种方法，否则没有方法
            if (restTarget == 0) {
                return 1;
            }
            return 0;
        }

        // 当前值前面放+
        int p1 = recursive(nums, index + 1, restTarget - nums[index]);
        // 当前值前面放—
        int p2 = recursive(nums, index + 1, restTarget - (-nums[index]));
        return p1 + p2;
    }

    /**
     * 这种写法要注意restTarget的边界问题
     * <p>
     * 数组的值均为非负整数，如果有负数，用dp方法可能有问题（边界不好找）
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        int neg = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            neg -= nums[i];
        }

        if (target > sum || target < neg) {
            return 0;
        }

        int[][] dp = new int[n + 1][sum + 1 - neg];
        // 从下到上，从右到左
        dp[n][-neg] = 1;

        for (int index = n - 1; index >= 0; index--) {
            for (int restTarget = neg; restTarget <= sum; restTarget++) {
                int num = nums[index];

                // 当前值前面放+
                int p1 = 0;
                if (restTarget - num >= neg && restTarget - num <= sum) {
                    p1 = dp[index + 1][restTarget - num - neg];
                }

                // 当前值前面放—
                int p2 = 0;
                if (restTarget + num >= neg && restTarget + num <= sum) {
                    p2 = dp[index + 1][restTarget + num - neg];
                }

                dp[index][restTarget - neg] = p1 + p2;
            }
        }

        return dp[0][target - neg];
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, -1};
        int target = 3;
        System.out.println(findTargetSumWays2(nums, target));
    }
}
