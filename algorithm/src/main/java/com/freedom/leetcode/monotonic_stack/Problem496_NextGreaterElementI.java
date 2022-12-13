package com.freedom.leetcode.monotonic_stack;

import java.util.HashMap;
import java.util.Map;

/**
 * 496. Next Greater Element I
 *
 * @author tobebetter9527
 * @create 2022/12/13 20:56
 */
public class Problem496_NextGreaterElementI {

  /**
   * time complexity is O(n + m), space complexity is O(n)
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n = nums2.length;
    Map<Integer, Integer> map = new HashMap<>(n);
    int[] stack = new int[n];
    int index = -1;
    for (int i = 0; i < n; i++) {
      int num = nums2[i];
      while (index != -1 && nums2[stack[index]] < num) {
        int idx = stack[index--];
        map.put(nums2[idx], num);
      }
      stack[++index] = i;
    }

    int m = nums1.length;
    int[] ans = new int[m];
    for (int i = 0; i < m; i++) {
      ans[i] = map.getOrDefault(nums1[i], -1);
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums1 = {2, 4}, nums2 = {1, 2, 3, 4};
    int[] ints = nextGreaterElement(nums1, nums2);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}
