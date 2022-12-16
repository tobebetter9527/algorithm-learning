package com.freedom.leetcode.monotonic_stack;

/**
 * 84. Largest Rectangle in Histogram
 *
 * @author tobebetter9527
 * @create 2022/12/15 23:08
 */
public class Problem84_LargestRectangleinHistogram {

  public static int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] stack = new int[n];
    int index = -1;
    int max = 0;

    for (int i = 0; i < n; i++) {
      while (index != -1 && heights[i] < heights[stack[index]]) {
        // pop element
        int popIdx = stack[index--];
        // peek element
        int j = index == -1 ? -1 : stack[index];
        int width = i - 1 - j;
        int height = heights[popIdx];
        max = Math.max(max, width * height);
      }
      stack[++index] = i;
    }

    while (index != -1) {
      int popIdx = stack[index--];
      int j = index == -1 ? -1 : stack[index];
      int width = n - 1 - j;
      int height = heights[popIdx];
      max = Math.max(max, width * height);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] heights = {5, 30, 30};
    System.out.println(largestRectangleArea(heights));

  }
}
