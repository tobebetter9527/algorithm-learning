package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. Subsets
 */
public class Problem78_Subsets {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Problem78_Subsets problem = new Problem78_Subsets();
        List<List<Integer>> subsets = problem.subsets(nums);
        System.out.println(subsets);
    }

    public List<List<Integer>> subsets(int[] nums) {
        process(nums, 0);
        return ans;
    }

    private void process(int[] nums, int start) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        // 要当前的值
        list.add(nums[start]);
        process(nums, start + 1);
        list.remove(list.size() - 1);
        // 不要当前的值
        process(nums, start + 1);
    }
}
