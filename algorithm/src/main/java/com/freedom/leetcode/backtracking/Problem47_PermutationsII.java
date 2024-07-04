package com.freedom.leetcode.backtracking;

import java.util.*;

/**
 * 47. Permutations II
 *
 * @author tobebetter9527
 * @create 2022/11/18 20:53
 */
public class Problem47_PermutationsII {


    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        Problem47_PermutationsII problem = new Problem47_PermutationsII();
        List<List<Integer>> permute = problem.permuteUnique(nums);
        System.out.println(permute);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        process2(ans, path, nums, used, 0, nums.length);
        return ans;
    }

    private void process(List<List<Integer>> ans, List<Integer> path, int[] nums, boolean[] used, int index, int n) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (used[i] || set.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            set.add(nums[i]);
            process(ans, path, nums, used, index + 1, n);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    private void process2(List<List<Integer>> ans, List<Integer> path, int[] nums, boolean[] used, int index, int n) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 这里不好理解
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            process2(ans, path, nums, used, index + 1, n);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
