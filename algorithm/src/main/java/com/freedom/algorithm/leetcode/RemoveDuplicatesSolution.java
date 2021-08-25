package com.freedom.algorithm.leetcode;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be
 * placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not matter what you leave beyond the
 * first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra
 * memory.
 * <p>
 * 作者：力扣 (LeetCode) 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/ 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author tobebetter9527
 * @create 2021/08/24 23:58
 */
public class RemoveDuplicatesSolution {

  public static int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int index = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[index]) {
        nums[++index] = nums[i];
      }
    }
    return ++index;
  }

  public static void main(String[] args) {
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int newLength = removeDuplicates(nums);
    for (int i = 0; i < newLength; i++) {
      System.out.println(nums[i]);
    }

  }
}
