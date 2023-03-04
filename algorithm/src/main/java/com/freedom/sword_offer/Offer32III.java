package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tobebetter9527
 * @create 2023/03/04 11:21
 */
public class Offer32III {

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> ans = new LinkedList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean flag = false;
    while (!queue.isEmpty()) {
      Deque<Integer> list = new LinkedList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (flag) {
          list.addFirst(node.val);
        } else {
          list.addLast(node.val);
        }
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }

      ans.add(new ArrayList<>(list));
      flag = !flag;
    }
    return ans;
  }

  public List<List<Integer>> levelOrder2(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> ans = new LinkedList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean flag = false;
    while (!queue.isEmpty()) {
      List<Integer> list = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        list.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      if (flag) {
        Collections.reverse(list);
      }
      ans.add(list);
      flag = !flag;
    }
    return ans;
  }
}
