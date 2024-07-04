package com.freedom.sword_offer;

public class Offer33 {

    public boolean verifyPostorder(int[] postorder) {
        return recursive(postorder, 0, postorder.length - 1);
    }

    private boolean recursive(int[] postorder, int start, int end) {
        // 没有节点或只有一个节点，肯定是BST
        if (start >= end) {
            return true;
        }
        // root node
        int root = postorder[end];
        // search left sub tree
        int p = start;
        while (postorder[p] < root) {
            p++;
        }
        // search right sub tree
        int m = p;
        while (postorder[p] > root) {
            p++;
        }
        return p == end && recursive(postorder, start, m - 1) && recursive(postorder, m, end - 1);
    }
}
