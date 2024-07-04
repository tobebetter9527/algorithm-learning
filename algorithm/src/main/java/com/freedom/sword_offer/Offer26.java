package com.freedom.sword_offer;

public class Offer26 {

    /**
     * time complexity is O(nm), space complexity is O(n+m)
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        return isSameStructure(a, b) || isSubStructure(a.left, b) || isSubStructure(a.right, b);
    }

    private static boolean isSameStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return isSameStructure(a.left, b.left) && isSameStructure(a.right, b.right);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode a1 = new TreeNode(4);
        TreeNode a2 = new TreeNode(5);
        TreeNode a3 = new TreeNode(1);
        TreeNode a4 = new TreeNode(2);
        a.left = a1;
        a.right = a2;
        a1.left = a3;
        a1.right = a4;

        TreeNode b = new TreeNode(4);
        TreeNode b1 = new TreeNode(1);
        b.left = b1;

        System.out.println(isSubStructure(a, b));
    }

}
