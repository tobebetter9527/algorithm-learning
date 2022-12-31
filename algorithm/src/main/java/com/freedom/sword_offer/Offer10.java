package com.freedom.sword_offer;

/**
 * Offer 10
 *
 * @author tobebetter9527
 * @create 2022/12/31 18:54
 */
public class Offer10 {

  public int fib(int n) {
    if (n < 2) {
      return n;
    }

    int[] ans = new int[n + 1];
    ans[0] = 0;
    ans[1] = 1;
    for (int i = 2; i <= n; i++) {
      ans[i] = (ans[i - 1] + ans[i - 2]) % 1000000007;
    }
    return ans[n];
  }

  public static void main(String[] args) {
    System.out.println( 1 % 1000000007);
  }
}
