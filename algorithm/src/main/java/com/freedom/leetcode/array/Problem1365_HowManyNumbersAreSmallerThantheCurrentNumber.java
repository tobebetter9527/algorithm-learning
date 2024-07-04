package com.freedom.leetcode.array;

import java.util.Arrays;

/**
 * 1365. How Many Numbers Are Smaller Than the Current Number
 *
 * @author tobebetter9527
 * @create 2022/12/15 21:03
 */
public class Problem1365_HowManyNumbersAreSmallerThantheCurrentNumber {


    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int res = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && nums[i] > nums[j]) {
                    res++;
                }
            }
            ans[i] = res;
        }
        return ans;
    }

    /**
     * time complexity is O(nlogn), space complexity is O(n)
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;

        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, (x, y) -> x[0] - y[0]);

        int[] ans = new int[n];
        int pre = 0;
        ans[data[0][1]] = pre;
        for (int i = 1; i < n; i++) {
            if (data[i][0] != data[i - 1][0]) {
                pre = i;
            }
            ans[data[i][1]] = pre;
        }

        return ans;
    }


    /**
     * time complexity is O(n + k), space complexity is O(k)
     * <p>
     * 计数排序
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent3(int[] nums) {
        int n = nums.length;

        int[] cnt = new int[101];
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }

        // 累加
        for (int i = 1; i < 101; i++) {
            cnt[i] += cnt[i - 1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
        int[] ints = smallerNumbersThanCurrent3(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
}
