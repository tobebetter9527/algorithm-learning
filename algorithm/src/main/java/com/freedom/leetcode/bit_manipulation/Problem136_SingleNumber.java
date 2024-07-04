package com.freedom.leetcode.bit_manipulation;

/**
 * Single Number
 * <p>
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * @author tobebetter9527
 * @create 2022/06/02 22:03
 */
public class Problem136_SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

}
