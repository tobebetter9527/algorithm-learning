package com.freedom.leetcode.dp;

import com.freedom.leetcode.binary_tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 337. House Robber III
 *
 * @author tobebetter9527
 * @create 2022/12/08 20:47
 */
public class Problem337_HouseRobberIII {

  public int rob(TreeNode root) {
    return recursive(root);
  }

  private int recursive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // 不取当前的值
    int p1 = recursive(root.left) + recursive(root.right);

    // 取当前的值
    int p2 = root.val;
    if (root.left != null) {
      p2 += recursive(root.left.left) + recursive(root.left.right);
    }
    if (root.right != null) {
      p2 += recursive(root.right.left) + recursive(root.right.right);
    }
    return Math.max(p1, p2);
  }


  public int rob2(TreeNode root) {
    Map<TreeNode, Integer> cache = new HashMap<>();
    return recursive2(root, cache);
  }

  private int recursive2(TreeNode root, Map<TreeNode, Integer> cache) {
    if (root == null) {
      return 0;
    }
    if (cache.containsKey(root)) {
      return cache.get(root);
    }

    // 不取当前的值
    int p1 = recursive2(root.left, cache) + recursive2(root.right, cache);

    // 取当前的值
    int p2 = root.val;
    if (root.left != null) {
      p2 += recursive2(root.left.left, cache) + recursive2(root.left.right, cache);
    }
    if (root.right != null) {
      p2 += recursive2(root.right.left, cache) + recursive2(root.right.right, cache);
    }

    int res = Math.max(p1, p2);
    cache.put(root, res);
    return res;
  }

  public int rob3(TreeNode root) {
    int[] res = recursive3(root);
    return Math.max(res[0], res[1]);
  }

  private int[] recursive3(TreeNode root) {
    int[] res = new int[2];
    if (root == null) {
      return res;
    }
    // 后续遍历
    int[] left = recursive3(root.left);
    int[] right = recursive3(root.right);
    // 不选
    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    // 选
    res[1] = root.val + left[0] + right[0];
    return res;
  }


  public static void main(String[] args) {

  }
}
