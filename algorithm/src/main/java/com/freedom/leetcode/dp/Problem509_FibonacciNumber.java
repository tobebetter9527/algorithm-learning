package com.freedom.leetcode.dp;

/**
 * 509. Fibonacci Number
 *
 * @author tobebetter9527
 * @create 2022/12/01 20:50
 */
public class Problem509_FibonacciNumber {

  public int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  public int fib2(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    int[] arr = new int[n + 1];
    arr[0] = 0;
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }

}
