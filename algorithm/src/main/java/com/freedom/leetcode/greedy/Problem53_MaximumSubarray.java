package com.freedom.leetcode.greedy;

/**
 * 53. Maximum Subarray
 *
 * @author tobebetter9527
 * @create 2022/11/22 20:19
 */
public class Problem53_MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Problem53_MaximumSubarray problem = new Problem53_MaximumSubarray();
        int i = problem.maxSubArray(nums);
        System.out.println(i);
    }

    /**
     * time complexity is O(N),sapce complexity is O(1)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
