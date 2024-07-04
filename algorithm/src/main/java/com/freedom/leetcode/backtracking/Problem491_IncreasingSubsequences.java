package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 491. Increasing Subsequences
 *
 * @author tobebetter9527
 * @create 2022/11/17 20:43
 */
public class Problem491_IncreasingSubsequences {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7, 7};
        Problem491_IncreasingSubsequences problem = new Problem491_IncreasingSubsequences();
        List<List<Integer>> subsequences = problem.findSubsequences(nums);
        System.out.println(subsequences);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        process(nums, Integer.MIN_VALUE, 0);
        return ans;
    }

    private void process(int[] nums, int preValue, int curIndex) {
        if (curIndex == nums.length) {
            if (list.size() > 1) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        // 选当前元素，限制条件：只有当前值大于等于之前的值
        if (nums[curIndex] >= preValue) {
            list.add(nums[curIndex]);
            process(nums, nums[curIndex], curIndex + 1);
            list.remove(list.size() - 1);
        }

        // 不选当前元素
        if (nums[curIndex] != preValue) {
            process(nums, preValue, curIndex + 1);
        }
    }
}
