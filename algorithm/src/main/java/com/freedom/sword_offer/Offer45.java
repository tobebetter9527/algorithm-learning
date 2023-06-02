package com.freedom.sword_offer;

import java.util.Arrays;

public class Offer45 {

  public static String minNumber(int[] nums) {
    String[] arr = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      arr[i] = nums[i] + "";
    }
    Arrays.sort(arr, (x, y) -> (x + y).compareTo(y + x));
    StringBuilder sb = new StringBuilder();
    Arrays.stream(arr).forEach(x -> sb.append(x));
    return sb.toString();
  }

  public static void main(String[] args) {
    int[] nums = {3, 30, 34, 5, 9};
    System.out.println(minNumber(nums));
  }
}
