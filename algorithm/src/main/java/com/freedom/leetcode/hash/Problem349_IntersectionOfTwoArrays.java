package com.freedom.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 349. Intersection of Two Arrays
 *
 * @author tobebetter9527
 * @create 2022/10/19 22:18
 */
public class Problem349_IntersectionOfTwoArrays {


    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                set2.add(i);
            }
        }

        int[] ans = new int[set2.size()];
        int index = 0;
        for (Integer value : set2) {
            ans[index++] = value;
        }

        return ans;
    }

}
