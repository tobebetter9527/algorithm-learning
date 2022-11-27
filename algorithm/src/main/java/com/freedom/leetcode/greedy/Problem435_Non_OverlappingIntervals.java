package com.freedom.leetcode.greedy;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals
 *
 * @author tobebetter9527
 * @create 2022/11/27 10:08
 */
public class Problem435_Non_OverlappingIntervals {

  /**
   * time complexity is O(nlogn), space complexity is O(logn).
   *
   * @param intervals
   * @return
   */
  public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
    int count = 0;
    int pos = Integer.MIN_VALUE;
    for (int i = 0; i < intervals.length; i++) {
      int[] interval = intervals[i];
      if (interval[0] >= pos) {
        count++;
        pos = interval[1];
      }
    }
    return intervals.length - count;
  }

  public static void main(String[] args) {
    int[] arr1 = {1, 2};
    int[] arr2 = {2, 3};

    int[][] intervals = new int[2][2];
    intervals[1] = arr1;
    intervals[0] = arr2;

    Problem435_Non_OverlappingIntervals problem = new Problem435_Non_OverlappingIntervals();
    int i = problem.eraseOverlapIntervals(intervals);
    System.out.println(i);

  }


}
