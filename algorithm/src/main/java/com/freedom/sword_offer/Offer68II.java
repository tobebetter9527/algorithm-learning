package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tobebetter9527static
 * @create 2023/03/05 8:50
 */
public class Offer68II {

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    node1.left = node2;
    TreeNode node = lowestCommonAncestor2(node1, node2, node1);
    System.out.println(node);

  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == p || root == q || root == null) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }


  public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    handleMap(root, parentMap);

    Set<TreeNode> set = new HashSet<>();
    set.add(p);
    TreeNode cur = p;
    while (parentMap.get(cur) != null) {
      cur = parentMap.get(cur);
      set.add(cur);
    }

    cur = q;
    while (!set.contains(cur)) {
      cur = parentMap.get(cur);
    }
    return cur;
  }

  private static void handleMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      parentMap.put(root.left, root);
      handleMap(root.left, parentMap);
    }
    if (root.right != null) {
      parentMap.put(root.right, root);
      handleMap(root.right, parentMap);
    }
  }
}
