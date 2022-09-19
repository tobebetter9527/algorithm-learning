package com.freedom.zuo.class26_fibonacci;

/**
 * 斐波那契数列的求解方法
 *
 * @author tobebetter9527
 * @create 2022/07/30 18:25
 */
public class Code02_FibonacciProblem {

  /**
   * 递归求解
   *
   * @param n
   * @return
   */
  public static long f1(long n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    return f1(n - 1) + f1(n - 2);
  }

  // ----------------------------------------------- //

  /**
   * 动态规划
   *
   * @param n
   * @return
   */
  public static long f2(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    long[] arr = new long[n + 1];
    arr[1] = 1;
    arr[2] = 1;

    for (int i = 3; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }

    return arr[n];
  }

  // ----------------------------------------------- //

  /**
   * 非递归版本
   *
   * @param n
   * @return
   */
  public static long f3(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    long pre = 1;
    long prepre = 1;
    long temp = 0;
    for (int i = 3; i <= n; i++) {
      temp = pre + prepre;
      prepre = pre;
      pre = temp;
    }

    return temp;
  }

  // ----------------------------------------------- //

  /**
   * 矩阵法
   *
   * @param n
   * @return
   */
  public static long f4(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    long[][] base = {{1, 1}, {1, 0}};
    int power = n - 2;

    long[][] res = matrixPower(base, power);
    return res[0][0] + res[1][0];
  }

  private static long[][] matrixPower(long[][] base, int power) {
    long[][] res = new long[base.length][base[0].length];
    for (int i = 0; i < base.length; i++) {
      res[i][i] = 1;
    }

    for (; power != 0; power >>= 1) {
      if ((power & 1) != 0) {
        res = matrix(res, base);
      }
      // 矩阵相乘
      base = matrix(base, base);
    }

    return res;
  }

  private static long[][] matrix(long[][] res, long[][] base) {
    long[][] ans = new long[res.length][base[0].length];

    int row = res.length;
    int col = base[0].length;
    int k = res[0].length; // res的列数也是base的行数

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        for (int l = 0; l < k; l++) {
          ans[i][j] += res[i][l] * base[j][l];
        }
      }
    }

    return ans;
  }


  public static void main(String[] args) {
    int n = 40;
    long start = System.nanoTime();
    System.out.println(f1(n));
    long end = System.nanoTime();
    System.out.println("递归：" + (end - start));

    long start2 = System.nanoTime();
    System.out.println(f2(n));
    long end2 = System.nanoTime();
    System.out.println("动态规划：" + (end2 - start2));

    long start3 = System.nanoTime();
    System.out.println(f3(n));
    long end3 = System.nanoTime();
    System.out.println("非递归：" + (end3 - start3));

    long start4 = System.nanoTime();
    System.out.println(f4(n));
    long end4 = System.nanoTime();
    System.out.println("矩阵：" + (end4 - start4));
  }
}
