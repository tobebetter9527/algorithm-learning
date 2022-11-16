package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 90. Subsets II
 *
 * @author tobebetter9527
 * @create 2022/11/16 20:46
 */
public class Problem90_SubsetsII {

  List<List<Integer>> ans = new LinkedList<>();
  List<Integer> path = new ArrayList<>();

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }
    int index = 0;
    int[] coins = new int[map.size()];
    int[] counts = new int[map.size()];
    for (Entry<Integer, Integer> entry : map.entrySet()) {
      coins[index] = entry.getKey();
      counts[index] = entry.getValue();
      index++;
    }
    process(coins, counts, 0);
    return ans;
  }

  private void process(int[] coins, int[] counts, int startIndex) {
    if (startIndex == coins.length) {
      ans.add(new ArrayList<>(path));
      return;
    }

    for (int i = 0; i <= counts[startIndex]; i++) {
      for (int j = 0; j < i; j++) {
        path.add(coins[startIndex]);
      }
      process(coins, counts, startIndex + 1);
      for (int j = 0; j < i; j++) {
        path.remove(path.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 2};
    Problem90_SubsetsII problem = new Problem90_SubsetsII();
    List<List<Integer>> lists = problem.subsetsWithDup(nums);
    System.out.println(lists);
  }
}
