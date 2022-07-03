package com.freedom.zuo.class12_binary_tree;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 *
 * @author tobebetter9527
 * @create 2022/07/03 11:02
 */
public class Code05_MaxSubBSTSize {

  public static int largestBSTSubtree(TreeNode head) {
    if (head == null) {
      return 0;
    }

    return process(head).maxSubBstSize;
  }

  private static Info process(TreeNode node) {
    if (node == null) {
      return new Info(0, 0, null, null);
    }
    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);

    int allNodes = leftInfo.allNodes + leftInfo.allNodes + 1;

    int leftInfoMax = leftInfo.max != null ? leftInfo.max : Integer.MIN_VALUE;
    int rightInfoMax = rightInfo.max != null ? rightInfo.max : Integer.MIN_VALUE;
    int max = Math.max(node.val, Math.max(leftInfoMax, rightInfoMax));

    int leftInfoMin = leftInfo.min != null ? leftInfo.min : Integer.MAX_VALUE;
    int rightInfoMin = rightInfo.min != null ? rightInfo.min : Integer.MAX_VALUE;
    int min = Math.max(node.val, Math.min(leftInfoMin, rightInfoMin));

    // 假设当前节点的最大BST不经过当前节点
    int p1 = leftInfo.maxSubBstSize;
    int p2 = rightInfo.maxSubBstSize;

    // 假设当前节点的最大BST经过当前节点
    int p3 = Integer.MIN_VALUE;
    // 首先左右子树必须是BST
    boolean isLeftBST = leftInfo.maxSubBstSize == leftInfo.allNodes;
    boolean isRightBST = rightInfo.maxSubBstSize == rightInfo.allNodes;
    // 如果左右子树都是BST,就判断当前节点是否为BST
    if (isLeftBST && isRightBST) {
      if (leftInfoMax < node.val && rightInfoMin > node.val) {
        p3 = leftInfo.allNodes + rightInfo.allNodes + 1;
      }
    }

    return new Info(Math.max(p3, Math.max(p1, p2)), allNodes, max, min);
  }


  static class Info {

    int maxSubBstSize;
    int allNodes;
    Integer max;
    Integer min;

    public Info(int maxSubBstSize, int allNodes, Integer max, Integer min) {
      this.maxSubBstSize = maxSubBstSize;
      this.allNodes = allNodes;
      this.max = max;
      this.min = min;
    }
  }


  static class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
      val = value;
    }
  }
}
