package com.freedom.leetcode.binary_tree;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 *
 * @author tobebetter9527
 * @create 2022/11/12 20:54
 */
public class Problem108_ConvertSortedArraytoBinarySearchTree {


  public static TreeNode sortedArrayToBST(int[] nums) {
    return process(nums, 0, nums.length - 1);
  }

  private static TreeNode process(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) >> 1;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = process(nums, start, mid - 1);
    root.right = process(nums, mid + 1, end);
    return root;
  }

  public static void main(String[] args) {
    int[] nums = {-10, -3, 0, 5, 9};
    TreeNode node = sortedArrayToBST(nums);
    System.out.println(node);
  }
}
