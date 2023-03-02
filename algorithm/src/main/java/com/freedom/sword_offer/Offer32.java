package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tobebetter9527
 * @create 2023/03/01 20:22
 */
public class Offer32 {

  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> ans = new LinkedList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      List<Integer> list = new LinkedList<>();
      for (int i = 0, size = queue.size(); i < size; i++) {
        TreeNode node = queue.remove();
        list.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      ans.add(list);
    }
    return ans;
  }

  /**
   * 比较笨的实现
   *
   * @param root
   * @return
   */
  public static List<List<Integer>> levelOrder2(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> list = new LinkedList<>();

    TreeNode curLevel = root;
    TreeNode nextLevel = null;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      list.add(poll.val);
      if (poll != curLevel) {
        nextLevel = poll.right != null ? poll.right : (poll.left != null ? poll.left : nextLevel);
      } else {
        ans.add(list);
        list = new LinkedList<>();
        curLevel = poll.right != null ? poll.right : (poll.left != null ? poll.left : nextLevel);
        nextLevel = null;
      }

      if (poll.left != null) {
        queue.add(poll.left);
      }
      if (poll.right != null) {
        queue.add(poll.right);
      }
    }
    return ans;
  }


  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(9);
    TreeNode node3 = new TreeNode(20);
    TreeNode node4 = new TreeNode(15);
    TreeNode node5 = new TreeNode(7);

    node1.left = node2;
    node1.right = node3;
    node3.left = node4;
    node3.right = node5;
    List<List<Integer>> lists = levelOrder(node1);
    for (List<Integer> list : lists) {
      System.out.println(list);
    }

  }

}
