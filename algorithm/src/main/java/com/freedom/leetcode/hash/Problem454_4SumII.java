package com.freedom.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 *
 * @author tobebetter9527
 * @create 2022/10/20 20:47
 */
public class Problem454_4SumII {

    /**
     * time complexity is O(n<sup>2</sup>), space complexity is O(n<sup>2</sup>).
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i : nums1) {
            for (int j : nums2) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }

        int res = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                res += map.getOrDefault(0 - (i + j), 0);
            }
        }

        return res;
    }
}
