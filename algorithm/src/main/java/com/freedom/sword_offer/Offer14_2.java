package com.freedom.sword_offer;

/**
 * 题解：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/solutions/106190/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/?orderBy=most_votes
 *
 * @author tobebetter9527
 * @create 2023/02/04 22:02
 */
public class Offer14_2 {

  /**
   * # 求 (x^a) % p —— 循环求余法 def remainder(x, a, p): rem = 1 for _ in range(a): rem = (rem * x) % p return rem
   */
  public static int cuttingRope(int n) {
    if (n <= 3) {
      return n - 1;
    }
    // 无越界求法
    //    int quotient = n / 3, remainder = n % 3;
    //    if (remainder == 0) {
    //      return (int) Math.pow(3, quotient);
    //    } else if (remainder == 1) {
    //      return (int) (Math.pow(3, quotient - 1) * 4);
    //    } else {
    //      return (int) (Math.pow(3, quotient) * 2);
    //    }

    int quotient = n / 3 - 1, remainder = n % 3;
    long rem = 1, x = 3, p = 1000000007;
    for (int i = 0; i < quotient; i++) {
      rem = (rem * x) % p;
      //  x = (x * x) % p;
    }

    if (remainder == 0) {
      return (int) ((3 * rem) % p);
    } else if (remainder == 1) {
      return (int)  ((rem * 4) % p);
    } else {
      return (int)  ((rem * 3 * 2) % p);
    }
  }

  public static int cuttingRope2(int n) {
    if (n <= 3) {
      return n - 1;
    }
    int b = n % 3, p = 1000000007;
    long rem = 1, x = 3;
    for (int a = n / 3 - 1; a > 0; a /= 2) {
      if (a % 2 == 1) {
        rem = (rem * x) % p;
      }
      x = (x * x) % p;
    }
    if (b == 0) {
      return (int) (rem * 3 % p);
    }
    if (b == 1) {
      return (int) (rem * 4 % p);
    }
    return (int) (rem * 6 % p);
  }

  public static void main(String[] args) {
    int n = 120;
    System.out.println(cuttingRope(n));


  }



}
