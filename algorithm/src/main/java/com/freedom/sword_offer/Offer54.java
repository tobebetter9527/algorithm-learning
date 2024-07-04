package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tobebetter9527
 * @create 2023/03/03 21:44
 */
public class Offer54 {

    int k;
    int res;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (k == 0) {
            return;
        }
        if (--k == 0) {
            res = root.val;
        }
        dfs(root.left);
    }


    /**
     * 不好的解法
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest2(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        return list.get(list.size() - k).val;
    }

    private void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }
}
