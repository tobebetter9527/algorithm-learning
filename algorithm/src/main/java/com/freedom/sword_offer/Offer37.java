package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer37 {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    root.left = node2;
    root.right= node3;
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    node3.left= node4;
    node3.right = node5;

    Codec codec = new Codec();
    String serialize = codec.serialize(root);
    codec.deserialize(serialize);
  }


  static class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if (root == null) {
        return "[]";
      }
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node != null) {
          sb.append(node.val + ",");
          queue.offer(node.left);
          queue.offer(node.right);
        } else {
          sb.append("null,");
        }
      }
      sb.append("]");
      return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      if ("[]".equals(data)) {
        return null;
      }
      String[] split = data.substring(1, data.length() - 1).split(",");
      int idx = 0;
      TreeNode root = new TreeNode(Integer.parseInt(split[idx++]));
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty() && idx < split.length) {
        TreeNode node = queue.poll();
        if (!split[idx].equals("null")) {
          node.left = new TreeNode(Integer.parseInt(split[idx]));
          queue.add(node.left);
        }
        idx++;
        if (!split[idx].equals("null")) {
          node.right = new TreeNode(Integer.parseInt(split[idx]));
          queue.add(node.right);
        }
        idx++;
      }
      return root;
    }
  }
}
