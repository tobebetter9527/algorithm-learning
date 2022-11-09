package com.freedom.leetcode.binary_tree;

/**
 * 654. Maximum Binary Tree
 */
public class Problem654_MaximumBinaryTree {

  /**
   * time complexity is O(n^2), space complexity is O(n).
   * @param nums
   * @return
   */
  public static TreeNode constructMaximumBinaryTree(int[] nums) {
    return process(nums, 0, nums.length - 1);
  }

  private static TreeNode process(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }

    int maxIndex = start;
    for (int i = start + 1; i <= end; i++) {
      if (nums[maxIndex] < nums[i]) {
        maxIndex = i;
      }
    }

    TreeNode root = new TreeNode(nums[maxIndex]);
    root.left = process(nums, start, maxIndex - 1);
    root.right = process(nums, maxIndex + 1, end);
    return root;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 6, 0, 5};
    TreeNode treeNode = constructMaximumBinaryTree(nums);
    System.out.println(treeNode);
  }
}
