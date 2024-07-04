package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. Combination Sum
 */
public class Problem39_CombinationSum {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        Problem39_CombinationSum problem = new Problem39_CombinationSum();
        List<List<Integer>> lists = problem.combinationSum(candidates, target);
        System.out.println(lists);

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        process(candidates, target, 0, 0);
        return ans;
    }

    private void process(int[] candidates, int target, int sum, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (index >= candidates.length) {
            return;
        }
        int candidate = candidates[index];
        for (int i = 0; i * candidate <= target - sum; i++) {
            for (int j = 0; j < i; j++) {
                path.add(candidate);
            }
            process(candidates, target, sum + i * candidate, index + 1);
            for (int j = 0; j < i; j++) {
                path.remove(path.size() - 1);
            }
        }
    }
}
