package com.freedom.leetcode.backtracking;

import java.util.*;
import java.util.Map.Entry;

/**
 * 40. Combination Sum II
 *
 * @author tobebetter9527
 * @create 2022/11/15 20:08
 */
public class Problem40_CombinationSumII {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> map = new HashMap<>(candidates.length);
        for (int i = 0; i < candidates.length; i++) {
            map.put(candidates[i], map.getOrDefault(candidates[i], 0) + 1);
        }
        int index = 0;
        int[] coins = new int[map.size()];
        int[] counts = new int[map.size()];
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            counts[index] = entry.getValue();
            index++;
        }
        process(coins, counts, target, 0, 0);
        return ans;
    }

    private void process(int[] coins, int[] counts, int target, int sum, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (index >= coins.length) {
            return;
        }
        for (int i = 0; i <= counts[index] && i * coins[index] <= target - sum; i++) {
            for (int j = 0; j < i; j++) {
                path.add(coins[index]);
            }
            process(coins, counts, target, sum + i * coins[index], index + 1);
            for (int j = 0; j < i; j++) {
                path.remove(path.size() - 1);
            }
        }
    }
}
