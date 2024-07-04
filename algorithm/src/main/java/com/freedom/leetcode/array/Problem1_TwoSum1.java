package com.freedom.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and a integer target
 *
 * @author tobebetter9527
 * @create 2022/10/10 20:37
 */
public class Problem1_TwoSum1 {

    /**
     * time complexity is O(n^2)
     * <p>
     * space complexity is O(1)
     */
    public static int[] right(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * time complexity is O(n)
     * <p>
     * space complexity is O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }


}
