package com.freedom.zuo.class_monotonic_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 *
 * @author tobebetter9527
 * @create 2022/07/26 22:00
 */
public class Code01_MonotonousStack {


  /**
   * @param arr 数组，不重复
   * @return 每个数左侧和右侧离其最近的并且小于该数的数。
   */
  public static int[][] getNearLessNoRepeat(int[] arr) {
    if (arr == null) {
      return null;
    }
    int n = arr.length;
    int[][] ans = new int[n][2];

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
        Integer value = stack.pop();
        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
        ans[value][0] = leftLessIndex;
        ans[value][1] = i;
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      Integer value = stack.pop();
      int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
      ans[value][0] = leftLessIndex;
      ans[value][1] = -1;
    }

    return ans;
  }

  // ------------------------------------------------ //

  /**
   * @param arr 数组，可以重复
   * @return 每个数左侧和右侧离其最近的并且小于该数的数。
   */
  public static int[][] getNearLess(int[] arr) {
    if (arr == null) {
      return null;
    }

    int n = arr.length;
    int[][] res = new int[n][2];

    Stack<List<Integer>> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
        List<Integer> pop = stack.pop();
        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
        for (Integer value : pop) {
          res[value][0] = leftLessIndex;
          res[value][1] = i;
        }
      }

      if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
        stack.peek().add(i);
      } else {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        stack.push(list);
      }
    }

    while (!stack.isEmpty()) {
      List<Integer> pop = stack.pop();
      int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
      for (Integer value : pop) {
        res[value][0] = leftLessIndex;
        res[value][1] = -1;
      }
    }
    return res;
  }

  // ------------------------------ //

  /**
   * 暴力法
   *
   * @param arr 数组，可以重复
   * @return 每个数左侧和右侧离其最近的并且小于该数的数。
   */
  public static int[][] right(int[] arr) {
    if (arr == null) {
      return null;
    }

    int n = arr.length;
    int[][] res = new int[n][2];

    for (int i = 0; i < n; i++) {
      int leftLessIndex = -1;
      int rightLessIndex = -1;

      int cur = i - 1;
      while (cur >= 0) {
        if (arr[cur] >= arr[i]) {
          cur--;
        } else {
          leftLessIndex = cur;
          break;
        }
      }

      cur = i + 1;
      while (cur < n) {
        if (arr[cur] >= arr[i]) {
          cur++;
        } else {
          rightLessIndex = cur;
          break;
        }
      }

      res[i][0] = leftLessIndex;
      res[i][1] = rightLessIndex;
    }
    return res;
  }

  // -------------------------------- //

  public static void main(String[] args) {
    int size = 10;
    int max = 20;
    int testTimes = 2000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = getRandomArrayNoRepeat(size);
      int[] arr2 = getRandomArray(size, max);
      if (!isEqual(getNearLessNoRepeat(arr1), right(arr1))) {
        System.out.println("1Oops!");
        printArray(arr1);
        break;
      }
      if (!isEqual(getNearLess(arr2), right(arr2))) {
        System.out.println("2Oops!");
        printArray(arr2);
        break;
      }
    }
    System.out.println("测试结束");
  }


  // for test
  public static boolean isEqual(int[][] res1, int[][] res2) {
    if (res1.length != res2.length) {
      return false;
    }
    for (int i = 0; i < res1.length; i++) {
      if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
        return false;
      }
    }

    return true;
  }

  // for test
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }


  // for test
  public static int[] getRandomArrayNoRepeat(int size) {
    int[] arr = new int[(int) (Math.random() * size) + 1];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i;
    }
    for (int i = 0; i < arr.length; i++) {
      int swapIndex = (int) (Math.random() * arr.length);
      int tmp = arr[swapIndex];
      arr[swapIndex] = arr[i];
      arr[i] = tmp;
    }
    return arr;
  }

  // for test
  public static int[] getRandomArray(int size, int max) {
    int[] arr = new int[(int) (Math.random() * size) + 1];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
    }
    return arr;
  }

}
