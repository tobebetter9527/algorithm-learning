package com.freedom.leetcode.binary_tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/10 21:58
 */
public class Problem236_LowestCommonAncestorofaBinaryTree {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param root
   * @param p
   * @param q
   * @return
   */
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    generateParentMap(root, parentMap);

    Set<TreeNode> set = new HashSet<>();
    TreeNode temp = p;
    set.add(temp);
    while (parentMap.get(temp) != null) {
      temp = parentMap.get(temp);
      set.add(temp);
    }

    temp = q;
    while (!set.contains(temp)) {
      temp = parentMap.get(temp);
    }
    return temp;
  }

  private void generateParentMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      parentMap.put(root.left, root);
      generateParentMap(root.left, parentMap);
    }
    if (root.right != null) {
      parentMap.put(root.right, root);
      generateParentMap(root.right, parentMap);
    }
  }
}
