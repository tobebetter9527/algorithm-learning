package com.freedom.leetcode.array;

/**
 * 724. Find Pivot Index
 *
 * @author tobebetter9527
 * @create 2022/12/17 10:05
 */
public class Problem724_FindPivotIndex {

    /**
     * time complexity is O(n), space complexity is(n).
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int lessn = n - 1;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        // 处理0 index
        if (sums[lessn] - sums[0] == 0) {
            return 0;
        }

        for (int i = 1; i < n; i++) {
            if (sums[i - 1] == sums[lessn] - sums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * time complexity is O(n), space complexity is(1).
     *
     * @param nums
     * @return
     */
    public static int pivotIndex2(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += nums[i];
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if ((sum << 1) + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex2(nums));
    }
}
