package com.freedom.leetcode.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. Find Mode in Binary Search Tree
 *
 * @author tobebetter9527
 * @create 2022/11/10 21:20
 */
public class Problem501_FindModeinBinarySearchTree {

  List<Integer> list = new ArrayList<>();
  TreeNode pre;
  int maxCount;
  int count;

  public int[] findMode(TreeNode root) {
    inTraversal(root);
    int[] ans = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      ans[i] = list.get(i);
    }
    return ans;
  }

  private void inTraversal(TreeNode node) {
    if (node == null) {
      return;
    }
    inTraversal(node.left);

    if (pre == null || pre.val != node.val) {
      count = 1;
    } else {
      count++;
    }

    if (count == maxCount) {
      list.add(node.val);
    }
    if (count > maxCount) {
      list.clear();
      maxCount = count;
      list.add(node.val);
    }

    pre = node;
    inTraversal(node.right);
  }


}
