package com.freedom.leetcode.bit_manipulation;

/**
 * Single Number
 * <p>
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find
 * the single element and return it.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * 是zuo的KM的变体问题
 *
 * @author tobebetter9527
 * @create 2022/06/03 22:03
 */
public class SingleNumber_137 {

  public int singleNumber(int[] nums) {
    int single = 0;
    for (int i = 0; i < 32; i++) {
      int temp = 0;
      for (int num : nums) {
        temp += ((num >> i) & 1);
      }
      if (temp % 3 != 0) {
        single |= 1 << i;
      }
    }
    return single;
  }

}
