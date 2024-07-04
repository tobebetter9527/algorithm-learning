package com.freedom.leetcode.monotonic_stack;

/**
 * 503. Next Greater Element II
 *
 * @author tobebetter9527
 * @create 2022/12/13 21:28
 */
public class Problem503_NextGreaterElementII {

    /**
     * time complexity is O(n), space complexity is O(n).
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
        }
        int[] stack = new int[n << 1];
        int index = -1;

        // 相当于循环两次
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                while (index != -1 && nums[stack[index]] < num) {
                    int idx = stack[index--];
                    ans[idx] = num;
                }
                stack[++index] = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        int[] ints = nextGreaterElements(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
