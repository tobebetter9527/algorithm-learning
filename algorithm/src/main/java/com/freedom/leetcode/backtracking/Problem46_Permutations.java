package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. Permutations
 *
 * @author tobebetter9527
 * @create 2022/11/18 20:18
 */
public class Problem46_Permutations {

  List<List<Integer>> ans = new LinkedList<>();
  List<Integer> path = new ArrayList<>();
  boolean[] used;

  public List<List<Integer>> permute(int[] nums) {
    used = new boolean[nums.length];
    process(nums, used);
    return ans;
  }

  private void process(int[] nums, boolean[] used) {
    if (path.size() == nums.length) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      path.add(nums[i]);
      used[i] = true;
      process(nums, used);
      path.remove(path.size() - 1);
      used[i] = false;
    }
  }

  // ------------------- //

  public List<List<Integer>> permute2(int[] nums) {
    List<Integer> list = new ArrayList<>();
    for (int num : nums) {
      list.add(num);
    }
    backtrack(list, 0, nums.length);
    return ans;
  }

  private void backtrack(List<Integer> list, int index, int n) {
    if (index == n) {
      ans.add(new ArrayList<>(list));
      return;
    }
    for (int i = index; i < n; i++) {
      Collections.swap(list, i, index);
      backtrack(list, index + 1, n);
      Collections.swap(list, i, index);
    }
  }





  public static void main(String[] args) {
    int[] nums = {1, 2,3};
    Problem46_Permutations problem = new Problem46_Permutations();
    List<List<Integer>> permute = problem.permute2(nums);
    System.out.println(permute);
  }

}
