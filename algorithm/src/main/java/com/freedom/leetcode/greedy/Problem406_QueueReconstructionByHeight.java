package com.freedom.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. Queue Reconstruction by Height
 *
 * @author tobebetter9527
 * @create 2022/11/26 15:39
 */
public class Problem406_QueueReconstructionByHeight {

  /**
   * time complexity is O(n^2), time complexity is O(logn).
   *
   * @param people
   * @return
   */
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] == o2[0]) {
          return o1[1] - o2[1];
        }
        return o2[0] - o1[0];
      }
    });

    List<int[]> list = new ArrayList<>();
    for (int[] person : people) {
      list.add(person[1], person);
    }

    return list.toArray(new int[people.length][]);
  }

  public static void main(String[] args) {
    int[] arr1 = new int[2];
    arr1[0] = 7;
    arr1[1] = 0;
    int[] arr2 = new int[2];
    arr2[0] = 4;
    arr2[1] = 4;
    int[] arr3 = new int[2];
    arr3[0] = 7;
    arr3[1] = 1;
    int[] arr4 = new int[2];
    arr4[0] = 5;
    arr4[1] = 0;
    int[] arr5 = new int[2];
    arr5[0] = 6;
    arr5[1] = 1;
    int[] arr6 = new int[2];
    arr6[0] = 5;
    arr6[1] = 2;

    int[][] nums = new int[6][2];
    nums[0] = arr1;
    nums[1] = arr2;
    nums[2] = arr3;
    nums[3] = arr4;
    nums[4] = arr5;
    nums[5] = arr6;

    Problem406_QueueReconstructionByHeight problem = new Problem406_QueueReconstructionByHeight();
    int[][] ints = problem.reconstructQueue(nums);
    for (int[] anInt : ints) {
      System.out.println(anInt[0] + "," + anInt[1]);
    }
  }
}
