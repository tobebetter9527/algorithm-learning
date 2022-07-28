package com.freedom.zuo.class25_monotonic_stack;

import java.util.Stack;

/**
 * leetcode 84
 * <p>
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * @author tobebetter9527
 * @create 2022/07/28 21:46
 */
public class Code03_LargestRectangleInHistogram {

  public static int largestRectangleArea1(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int n = heights.length;
    int maxArea = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
        int j = stack.pop();
        int difX = stack.isEmpty() ? (i - 1 - 0 + 1) : (i - 1) - stack.peek();
        maxArea = Math.max(maxArea, heights[j] * difX);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      int j = stack.pop();
      int difX = stack.isEmpty() ? (n - 1 - 0 + 1) : (n - 1) - stack.peek();
      maxArea = Math.max(maxArea, heights[j] * difX);
    }

    return maxArea;
  }

  /**
   * 用数组替代栈
   *
   * @param height
   * @return
   */
  public static int largestRectangleArea2(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int N = height.length;
    int[] stack = new int[N];
    int si = -1;
    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
      while (si != -1 && height[i] <= height[stack[si]]) {
        int j = stack[si--];
        int k = si == -1 ? -1 : stack[si];
        int curArea = (i - k - 1) * height[j];
        maxArea = Math.max(maxArea, curArea);
      }
      stack[++si] = i;
    }
    while (si != -1) {
      int j = stack[si--];
      int k = si == -1 ? -1 : stack[si];
      int curArea = (height.length - k - 1) * height[j];
      maxArea = Math.max(maxArea, curArea);
    }
    return maxArea;
  }


  public static void main(String[] args) {
    int[] heights = {2, 1, 5, 6, 2, 3};
    int i = largestRectangleArea1(heights);
    System.out.println(i);
  }

}
