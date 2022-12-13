package com.freedom.leetcode.monotonic_stack;

import java.util.Stack;

/**
 * 739. Daily Temperatures
 *
 * @author tobebetter9527
 * @create 2022/12/13 20:15
 */
public class Problem739_DailyTemperatures {

  /**
   * time complexity is O(n^2),space complexity is O(1),
   *
   * @param temperatures
   * @return
   */
  public static int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      int temperature = temperatures[i];
      for (int j = i + 1; j < n; j++) {
        if (temperature < temperatures[j]) {
          ans[i] = j - i;
          break;
        }
      }
    }
    return ans;
  }

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param temperatures
   * @return
   */
  public static int[] dailyTemperatures2(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      int temperature = temperatures[i];
      while (!stack.isEmpty() && temperatures[stack.peek()] < temperature) {
        Integer index = stack.pop();
        ans[index] = i - index;
      }
      stack.push(i);
    }
    return ans;
  }

  public static int[] dailyTemperatures3(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];
    int[] stack = new int[n];
    int index = -1;
    for (int i = 0; i < n; i++) {
      int temperature = temperatures[i];
      while (index != -1 && temperatures[stack[index]] < temperature) {
        int idx = stack[index--];
        ans[idx] = i - idx;
      }
      stack[++index] = i;
    }
    return ans;
  }


  public static void main(String[] args) {
    int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
    int[] ints = dailyTemperatures3(temperatures);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}
