package com.freedom.leetcode.dp;

/**
 * 70. Climbing Stairs
 *
 * @author tobebetter9527
 * @create 2022/12/01 21:03
 */
public class Problem70_ClimbingStairs {

  public int climbStairs(int n) {
    if (n == 1 || n == 0) {
      return 1;
    }
    // 走一步
    int p1 = climbStairs(n - 1);
    int p2 = climbStairs(n - 2);
    return p1 + p2;
  }

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param n
   * @return
   */
  public int climbStairs2(int n) {
    if (n < 1) {
      return n;
    }
    int[] arr = new int[n + 1];
    arr[0] = 1;
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }


}
