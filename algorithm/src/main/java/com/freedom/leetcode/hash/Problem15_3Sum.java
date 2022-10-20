package com.freedom.leetcode.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 15. 3Sum
 *
 * @author tobebetter9527
 * @create 2022/10/20 21:38
 */
public class Problem15_3Sum {

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[k]);
            ans.add(list);
          }
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
    List<List<Integer>> lists = threeSum(nums);
    System.out.println(lists);
  }
}
