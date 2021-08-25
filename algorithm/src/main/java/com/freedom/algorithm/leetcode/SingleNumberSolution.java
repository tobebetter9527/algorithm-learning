package com.freedom.algorithm.leetcode;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/single-number 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author tobebetter9527
 * @create 2021/08/24 23:46
 */
public class SingleNumberSolution {

  public static int singleNumber(int[] nums) {
    int temp = 0;
    for (int i = 0; i < nums.length; i++) {
      temp ^= nums[i];
    }
    return temp;
  }

  public static int singleNumber1(int[] nums) {
    int temp = 0;
    for (int num : nums) {
      temp ^= num;
    }
    return temp;
  }


  public static void main(String[] args) {
    int[] nums = new int[]{7, 1, 2, 8, 1, 2, 8};
    System.out.println(singleNumber(nums));
  }
}
