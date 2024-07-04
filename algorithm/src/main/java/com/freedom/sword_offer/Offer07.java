package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树
 *
 * @author tobebetter9527
 * @create 2022/12/24 9:06
 */
public class Offer07 {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return recursive(map, preorder, 0, n - 1, 0, n - 1);
    }

    private static TreeNode recursive(Map<Integer, Integer> map, int[] preorder, int preStart, int preEnd,
                                      int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        int inIndex = map.get(rootValue);
        int size = inIndex - inStart;

        root.left = recursive(map, preorder, preStart + 1, preStart + size, inStart, inIndex - 1);
        root.right = recursive(map, preorder, preStart + size + 1, preEnd, inIndex + 1, inEnd);
        return root;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return recursive2(map, preorder, 0, 0, n - 1);
    }

    private static TreeNode recursive2(Map<Integer, Integer> map, int[] preorder, int rootIndex, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        int rootValue = preorder[rootIndex];
        int index = map.get(rootValue);
        TreeNode root = new TreeNode(rootValue);
        root.left = recursive2(map, preorder, rootIndex + 1, inLeft, index - 1);
        root.right = recursive2(map, preorder, rootIndex + (index - inLeft) + 1, index + 1, inRight);
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = {1, 2, 3}, inorder = {2, 3, 1};
        TreeNode treeNode = buildTree2(preorder, inorder);
        System.out.println(treeNode.val);
    }
}
