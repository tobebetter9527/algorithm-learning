package com.freedom.leetcode.greedy;

import java.util.Arrays;

/**
 * 1005. Maximize Sum Of Array After K Negations
 *
 * @author tobebetter9527
 * @create 2022/11/23 21:29
 */
public class Problem1005_MaximizeSumOfArrayAfterKNegations {

    public static void main(String[] args) {
        int[] nums = {4, 2, 3};
        int k = 1;
        Problem1005_MaximizeSumOfArrayAfterKNegations problem = new Problem1005_MaximizeSumOfArrayAfterKNegations();
        int i = problem.largestSumAfterKNegations3(nums, k);
        System.out.println(i);
    }

    /**
     * time complexity is O(m * nlogn),space complexity is O(1).
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < k; i++) {
            if (nums[0] < 0) {
                nums[0] = -nums[0];
                Arrays.sort(nums);
            } else {
                nums[0] = -nums[0];
            }

        }
        return Arrays.stream(nums).sum();
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        nums = Arrays.stream(nums).boxed().sorted((a, b) -> Math.abs(b) - Math.abs(a)).mapToInt(Integer::intValue)
                .toArray();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        if ((k & 1) == 1) {
            nums[n - 1] = -nums[n - 1];
        }
        return Arrays.stream(nums).sum();
    }

    public int largestSumAfterKNegations3(int[] nums, int k) {
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        Arrays.sort(nums);

        if ((k & 1) == 1) {
            nums[0] = -nums[0];
        }
        return Arrays.stream(nums).sum();
    }
}
