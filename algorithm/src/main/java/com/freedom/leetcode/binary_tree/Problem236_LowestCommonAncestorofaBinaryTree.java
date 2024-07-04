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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ancestor;
    }

    private Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info left = process(root.left, p, q);
        if (left.isFindP && left.isFindQ) {
            return left;
        }
        Info right = process(root.right, p, q);
        if (right.isFindP && right.isFindQ) {
            return right;
        }

        boolean isFindP = left.isFindP || right.isFindP || root == p;
        boolean isFindQ = left.isFindQ || right.isFindQ || root == q;
        return isFindP && isFindQ ? new Info(isFindP, isFindQ, root) : new Info(isFindP, isFindQ, null);
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

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

    static class Info {
        boolean isFindP;
        boolean isFindQ;
        TreeNode ancestor;

        public Info(boolean isFindP, boolean isFindQ, TreeNode ancestor) {
            this.isFindP = isFindP;
            this.isFindQ = isFindQ;
            this.ancestor = ancestor;
        }
    }
}
