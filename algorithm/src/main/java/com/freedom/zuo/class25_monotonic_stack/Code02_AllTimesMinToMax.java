package com.freedom.zuo.class25_monotonic_stack;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * <p>
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * <p>
 * 那么所有子数组中，这个值最大是多少？
 *
 * @author tobebetter9527
 * @create 2022/07/28 20:29
 */
public class Code02_AllTimesMinToMax {

  /**
   * 暴力法
   *
   * @param arr 数组,数值可以重复
   * @return (sub累加和)* (sub中的最小值)的最大值
   */
  public static int max1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int n = arr.length;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int sum = 0;
        int minValue = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
          sum += arr[k];
          minValue = Math.min(minValue, arr[k]);
        }
        max = Math.max(max, sum * minValue);
      }
    }

    return max;
  }

  //  --------------------------------------------------------------------------- //

  /**
   * 运用到累加和单调栈
   * <p>
   * 主要思想： 假设当前位置是当前子数组的最小值，子数组尽量往两边扩展，直到遇到比其更小的值
   *
   * @param arr 数组,数值可以重复
   * @return (sub累加和)* (sub中的最小值)的最大值
   */
  public static int max2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int n = arr.length;
    // 累加和
    int[] sums = new int[n];
    sums[0] = arr[0];
    for (int i = 1; i < n; i++) {
      sums[i] = sums[i - 1] + arr[i];
    }
    int max  = Integer.MIN_VALUE;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      // 这里的重复值为什么不像之前的，用链表解决
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        // 此时把j位置的值当作当前子数组最小值，尽量向两边扩展
        int j = stack.pop();
        //  stack.isEmpty() 说明左边的值都比arr[j]大，
        int difSum = stack.isEmpty() ? sums[i - 1] : sums[i - 1] - sums[stack.peek()];
        max = Math.max(max, difSum * arr[j]);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      int j = stack.pop();
      int difSum = stack.isEmpty() ? sums[n - 1] : sums[n - 1] - sums[stack.peek()];
      max = Math.max(max, difSum * arr[j]);
    }

    return max;
  }

  // ------------------------------------//

  public static int[] gerenareRondomArray() {
    int[] arr = new int[(int) (Math.random() * 20) + 10];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * 101);
    }
    return arr;
  }

  public static void main(String[] args) {
    int testTimes = 2000000;
    System.out.println("test begin");
    for (int i = 0; i < testTimes; i++) {
      int[] arr = gerenareRondomArray();
      if (max1(arr) != max2(arr)) {
        System.out.println("FUCK!");
        break;
      }
    }
    System.out.println("test finish");



  }
}
