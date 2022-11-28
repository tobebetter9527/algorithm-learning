package com.freedom.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals
 *
 * @author tobebetter9527
 * @create 2022/11/28 20:36
 */
public class Problem56_MergeIntervals {

  /**
   * time complexity is O(nlogn), space complexity is O(logn)
   *
   * @param intervals
   * @return
   */
  public int[][] merge(int[][] intervals) {
    List<int[]> list = new ArrayList<>();

    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    int min = intervals[0][0];
    int max = intervals[0][1];
    for (int i = 0; i < intervals.length; i++) {
      int[] interval = intervals[i];
      if (interval[0] > max) {
        int[] arr = {min, max};
        list.add(arr);

        min = interval[0];
        max = interval[1];
      } else {
        max = Math.max(max, interval[1]);
      }
    }
    list.add(new int[]{min, max});

    return list.toArray(new int[list.size()][]);
  }

  public static void main(String[] args) {
    int[] arr1 = {1, 3};
    int[] arr2 = {2, 6};
    int[] arr3 = {8, 10};
    int[] arr4 = {15, 18};
    int[][] intervals = new int[4][2];
    intervals[0] = arr1;
    intervals[1] = arr2;
    intervals[2] = arr3;
    intervals[3] = arr4;

    Problem56_MergeIntervals problem = new Problem56_MergeIntervals();
    int[][] merge = problem.merge(intervals);
    System.out.println(merge);
  }
}
