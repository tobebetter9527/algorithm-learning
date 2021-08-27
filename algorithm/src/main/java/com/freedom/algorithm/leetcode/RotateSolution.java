package com.freedom.algorithm.leetcode;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3 Output: [5,6,7,1,2,3,4] Explanation: rotate 1 steps to the right:
 * [7,1,2,3,4,5,6] rotate 2 steps to the right: [6,7,1,2,3,4,5] rotate 3 steps to the right: [5,6,7,1,2,3,4] Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2 Output: [3,99,-1,-100] Explanation: rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]  
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105 -231 <= nums[i] <= 231 - 1 0 <= k <= 105  
 * <p>
 * Follow up:
 * <p>
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * <p>
 * 作者：力扣 (LeetCode) 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/ 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author tobebetter9527
 * @create 2021/08/27 21:07
 */
public class RotateSolution {

  public static void rotate(int[] nums, int k) {
    if (nums == null || nums.length < 2) {
      return;
    }
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public static void reverse(int[] nums, int start, int end) {
    while (start < end) {
      swap(nums, start, end);
      start++;
      end--;
    }
  }

  public static void swap(int[] nums, int start, int end) {
    nums[start] = nums[start] ^ nums[end];
    nums[end] = nums[start] ^ nums[end];
    nums[start] = nums[start] ^ nums[end];
  }


  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5, 6, 7};
    int k = 3;
    rotate(nums, k);
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }

}
