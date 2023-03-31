package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tobebetter9527
 * @create 2023/03/25 20:55
 */
public class Offer34 {

  List<List<Integer>> ans;
  LinkedList<Integer> list;

  public List<List<Integer>> pathSum(TreeNode root, int target) {
    ans = new LinkedList<>();
    list = new LinkedList<>();
    backTrack(root, target);
    return ans;
  }

  private void backTrack(TreeNode root, int target) {
    if (root == null) {
      return;
    }

    list.add(root.val);
    target -= root.val;

    if (target == 0 && root.left == null && root.right == null) {
      ans.add(new ArrayList<>(list));
    }
    backTrack(root.left, target);
    backTrack(root.right, target);
    list.removeLast();
  }


  public static void main(String[] args) {
    int target = 3;
    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(3);
    root.left = node1;
    root.right = node2;

    Offer34 offer34 = new Offer34();
    List<List<Integer>> lists = offer34.pathSum(root, target);
    System.out.println(lists);
  }


}
