package com.freedom.leetcode.array;

/**
 * Minimum Size Subarray Sum
 *
 * @author tobebetter9527
 * @create 2022/10/13 20:45
 */
public class Problem209_MinimumSizeSubarraySum {

    public static int right(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * time complexity is O(n), space complexity is O(1).
     */
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // left 为滑动窗口的起点
        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;
        // right 为滑动窗口的终点
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLength = 20;
        int maxValue = 20;
        for (int i = 0; i < testTimes; i++) {
            int[] nums = generateNums(maxLength, maxValue);
            int target = generateValue(maxValue);

            int right = right(target, nums);
            int min = minSubArrayLen(target, nums);
            if (right != min) {
                System.out.println("wrong target=" + target);
                printArray(nums);
                System.out.println("======");
            }
        }
        System.out.println("Done!");
    }

    private static int[] generateNums(int maxLength, int maxValue1) {
        int length = generateValue(maxLength);
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = generateValue(maxValue1);
        }
        return nums;
    }


    private static int generateValue(int maxValue2) {
        return (int) (Math.random() * (maxValue2 + 1)) + 1;
    }

    private static void printArray(int[] nums) {
        if (nums != null) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
        }
    }
}
