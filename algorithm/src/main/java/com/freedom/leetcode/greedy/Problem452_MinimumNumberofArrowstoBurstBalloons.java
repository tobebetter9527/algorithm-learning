package com.freedom.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 *
 * @author tobebetter9527
 * @create 2022/11/26 17:41
 */
public class Problem452_MinimumNumberofArrowstoBurstBalloons {

  public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, new Comparator<int[]>() {
      @Override
      public int compare(int[] point1, int[] point2) {
        if (point1[1] > point2[1]) {
          return 1;
        } else if (point1[1] < point2[1]) {
          return -1;
        } else {
          return 0;
        }
      }
    });
    int ans = 1;
    int pos = points[0][1];
    for (int[] point : points) {
      if (point[0] > pos) {
        ans++;
        pos = point[1];
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] arr1 = new int[2];
    arr1[0] = -2147483646;
    arr1[1] = -2147483645;
    int[] arr2 = new int[2];
    arr2[0] = 2147483646;
    arr2[1] = 2147483647;

    int[][] nums = new int[2][2];
    nums[0] = arr1;
    nums[1] = arr2;


    Problem452_MinimumNumberofArrowstoBurstBalloons problem = new Problem452_MinimumNumberofArrowstoBurstBalloons();
    int ints = problem.findMinArrowShots(nums);
    System.out.println(ints);
  }
}
