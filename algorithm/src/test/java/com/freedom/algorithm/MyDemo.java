package com.freedom.algorithm;

import java.util.Arrays;

public class MyDemo {

  public static void main(String[] args) {
    int[] nums = {1,1,1,1,1};
    System.out.println(unequalTriplets(nums));

  }

  public static int unequalTriplets(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (nums[i] != nums[j]) {
          for (int k = j + 1; k < n; k++) {
            if (nums[j] != nums[k]) {
              count++;
            }
          }
        }
      }
    }
    return count;
  }




}

