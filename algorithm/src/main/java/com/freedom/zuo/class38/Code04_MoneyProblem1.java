package com.freedom.zuo.class38;

/**
 * int[] d，d[i]：i号怪兽的能力
 * <p>
 * int[] p，p[i]：i号怪兽要求的钱
 * <p>
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * <p>
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
 * <p>
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，
 * <p>
 * 他的能力直接累加到你的能力上。
 * <p>
 * 返回通过所有的怪兽，需要花的最小钱数。
 *
 * @author tobebetter9527
 * @create 2022/08/17 21:27
 */
public class Code04_MoneyProblem1 {

  /**
   * 如果能力的累加比较少，选择此算法
   *
   * @param d d[i]：i号怪兽的能力
   * @param p p[i]：i号怪兽要求的钱
   * @return 通过所有怪兽，最最少的钱
   */
  public static long function1(int[] d, int[] p) {
    return process1(d, p, 0, 0);
  }

  /**
   * @param ability 当前能力
   * @param index   当前来到的位置
   */
  private static long process1(int[] d, int[] p, int ability, int index) {
    // 已经过关
    if (d.length == index) {
      return 0;
    }

    // 如果当前能力不及怪兽,只能贿赂
    if (ability < d[index]) {
      return p[index] + process1(d, p, ability + d[index], index + 1);
    } else {
      // 选择贿赂
      long p1 = p[index] + process1(d, p, ability + d[index], index + 1);

      // 选择不贿赂
      long p2 = process1(d, p, ability, index + 1);

      return Math.min(p1, p2);
    }
  }

  // ------------------------------------------------- //

  /**
   * function1的动态规划方法
   */
  public static long function2(int[] d, int[] p) {
    int sum = 0;
    for (int i : d) {
      sum += i;
    }

    long[][] dp = new long[d.length + 1][sum + 1];

    // 终止条件
    for (int i = 0; i <= sum; i++) {
      dp[d.length][i] = 0L;
    }

    // 从下到上，从左到右
    for (int index = d.length - 1; index >= 0; index--) {
      for (int ability = 0; ability <= sum; ability++) {
        if (ability + d[index] > sum) {
          continue;
        }

        if (ability < d[index]) {
          dp[index][ability] = p[index] + dp[index + 1][ability + d[index]];
        } else {
          long p1 = p[index] + dp[index + 1][ability + d[index]];
          long p2 = dp[index + 1][ability];
          dp[index][ability] = Math.min(p1, p2);
        }
      }
    }

    return dp[0][0];
  }

  // ---- -------------- -------------- ------------------ //

  // ---- -------------- -------------- ------------------ //

  public static int[][] generateTwoRandomArray(int len, int value) {
    int size = (int) (Math.random() * len) + 1;
    int[][] arrs = new int[2][size];
    for (int i = 0; i < size; i++) {
      arrs[0][i] = (int) (Math.random() * value) + 1;
      arrs[1][i] = (int) (Math.random() * value) + 1;
    }
    return arrs;
  }

  public static void main(String[] args) {
    int len = 10;
    int value = 20;
    int testTimes = 10000;
    for (int i = 0; i < testTimes; i++) {
      int[][] arrs = generateTwoRandomArray(len, value);
      int[] d = arrs[0];
      int[] p = arrs[1];
      long ans1 = function1(d, p);
      long ans2 = function2(d, p);
      //  long ans3 = func3(d, p);
      // long ans4 = minMoney2(d,p);
      if (ans1 != ans2) {
        System.out.println("oops!");
      }
    }

  }
}
