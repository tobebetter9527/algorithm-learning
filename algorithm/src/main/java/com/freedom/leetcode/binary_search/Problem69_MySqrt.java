package com.freedom.leetcode.binary_search;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is
 * returned.
 * <p>
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 * @author tobebetter9527
 * @create 2022/06/05 19:55
 */
public class Problem69_MySqrt {

  public static int mySqrt(int x) {
    if (x == 0) {
      return 0;
    }
    int ans = -1;
    int high = x;
    int low = 0;
    while (low <= high) {
      int mid = low + ((high - low) >> 1);
      if ((long) mid * mid <= x) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int i = mySqrt(2147395599);
    System.out.println(i);
  }
}
