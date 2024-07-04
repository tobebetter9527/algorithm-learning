package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * binary tree postorder traversal
 *
 * @author tobebetter9527
 * @create 2022/07/02 11:51
 */
public class Problem145_BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        return traversal(root, list);
    }

    private List<Integer> traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        traversal(root.left, list);
        traversal(root.right, list);
        list.add(root.val);
        return list;
    }

}
