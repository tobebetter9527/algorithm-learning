package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tobebetter9527
 * @create 2023/03/04 10:59
 */
public class Offer32I {

  public int[] levelOrder(TreeNode root) {
    if (root == null) {
      return new int[0];
    }
    List<TreeNode> list = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      list.add(node);
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    int[] ans = new int[list.size()];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = list.get(i).val;
    }
    return ans;
  }
}
