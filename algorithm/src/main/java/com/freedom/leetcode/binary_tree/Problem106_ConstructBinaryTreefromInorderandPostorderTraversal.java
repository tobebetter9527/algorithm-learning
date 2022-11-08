package com.freedom.leetcode.binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 *
 * @author tobebetter9527
 * @create 2022/11/08 22:21
 */
public class Problem106_ConstructBinaryTreefromInorderandPostorderTraversal {


  public static TreeNode buildTree(int[] inorder, int[] postorder) {
    Map<Integer, Integer> inMap = new HashMap<>(inorder.length);
    for (int i = 0; i < inorder.length; i++) {
      inMap.put(inorder[i], i);
    }
    return process(inMap, 0, inorder.length - 1, inorder, 0, postorder.length - 1, postorder);
  }

  private static TreeNode process(Map<Integer, Integer> inMap, int inS, int inE,
      int[] inorder,
      int postS, int postE, int[] postorder) {
    if (inS > inE || postS > postE) {
      return null;
    }

    TreeNode root = new TreeNode();
    int value = postorder[postE];
    root.val = value;

    Integer inRootIndex = inMap.get(value);

    root.left = process(inMap, inS, inRootIndex - 1, inorder, postS, postS + (inRootIndex - 1 - inS),
        postorder);
    root.right = process(inMap, inRootIndex + 1, inE, inorder, postS + (inRootIndex - 1 - inS) + 1,
        postE - 1, postorder);

    return root;
  }

  public static void main(String[] args) {
    int[] inorder = {9, 3, 15, 20, 7};
    int[] postorder = {9, 15, 7, 20, 3};
    TreeNode node = buildTree(inorder, postorder);
    System.out.println(node);
  }
}
