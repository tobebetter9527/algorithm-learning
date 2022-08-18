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
  public static int function1(int[] d, int[] p) {
    return process1(d, p, 0, 0);
  }

  /**
   * @param ability 当前能力
   * @param index   当前来到的位置
   */
  private static int process1(int[] d, int[] p, int ability, int index) {
    // 已经过关
    if (d.length == index) {
      return 0;
    }

    // 如果当前能力不及怪兽,只能贿赂
    if (ability < d[index]) {
      return p[index] + process1(d, p, ability + d[index], index + 1);
    } else {
      // 选择贿赂
      int p1 = p[index] + process1(d, p, ability + d[index], index + 1);

      // 选择不贿赂
      int p2 = process1(d, p, ability, index + 1);

      return Math.min(p1, p2);
    }
  }

  // ------------------------------------------------- //

  /**
   * function1的动态规划方法
   */
  public static int function2(int[] d, int[] p) {
    int allAbility = 0;
    for (int i : d) {
      allAbility += i;
    }

    int[][] dp = new int[d.length + 1][allAbility + 1];

    // 终止条件
    for (int i = 0; i <= allAbility; i++) {
      dp[d.length][i] = 0;
    }

    // 从下到上，从左到右
    for (int index = d.length - 1; index >= 0; index--) {
      for (int ability = 0; ability <= allAbility; ability++) {
        if (ability + d[index] > allAbility) {
          continue;
        }

        if (ability < d[index]) {
          dp[index][ability] = p[index] + dp[index + 1][ability + d[index]];
        } else {
          int p1 = p[index] + dp[index + 1][ability + d[index]];
          int p2 = dp[index + 1][ability];
          dp[index][ability] = Math.min(p1, p2);
        }
      }
    }

    return dp[0][0];
  }

  // ---- -------------- -------------- ------------------ //

  /**
   * 如果钱的的累加比较少，选择此算法
   *
   * @param d d[i]：i号怪兽的能力
   * @param p p[i]：i号怪兽要求的钱
   * @return 通过所有怪兽，最最少的钱
   */
  public static int function3(int[] d, int[] p) {
    int allMoney = 0;
    for (int i = 0; i < p.length; i++) {
      allMoney += p[i];
    }

    int index = d.length - 1;
    for (int money = 0; money < allMoney; money++) {
      if (process3(d, p, index - 1, money) != -1) {
        return money;
      }
    }

    return allMoney;
  }

  /**
   * @param index 当前索引的位置
   * @param money 当前索引位置严格需要的钱
   * @return 能力值
   */
  private static int process3(int[] d, int[] p, int index, long money) {
    if (index == -1) {
      return money == 0 ? 0 : -1;
    }

    // 不贿赂当前怪兽
    int preAbility1 = process3(d, p, index - 1, money);
    int p1 = -1;
    if (preAbility1 != -1 && preAbility1 >= d[index]) {
      p1 = preAbility1;
    }

    // 贿赂当前怪兽
    int preAbility2 = process3(d, p, index - 1, money - p[index]);
    int p2 = -1;
    if (preAbility2 != -1) {
      p2 = preAbility2 + d[index];
    }

    return Math.max(p1, p2);
  }

  // ---- -------------- -------------- ------------------ //

  /**
   * 如果钱的的累加比较少，选择此算法
   * <p>
   * 动态规划
   *
   * @param d d[i]：i号怪兽的能力
   * @param p p[i]：i号怪兽要求的钱
   * @return 通过所有怪兽，最最少的钱
   */
  public static int function4(int[] d, int[] p) {
    int allMoney = 0;
    for (int i = 0; i < p.length; i++) {
      allMoney += p[i];
    }

    int[][] dp = new int[d.length][allMoney + 1];
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j <= allMoney; j++) {
        dp[i][j] = -1;
      }
    }
    // 对于第0行位置，必须贿赂p[0],获得能力d[0],其他位置都是-1; function3递归的截止条件不考虑
    dp[0][p[0]] = d[0];

    for (int index = 1; index < d.length; index++) {
      for (int money = 0; money <= allMoney; money++) {
        // 不贿赂当前怪兽
        int preAbility1 = dp[index - 1][money];
        int p1 = -1;
        if (preAbility1 != -1 && preAbility1 >= d[index]) {
          p1 = preAbility1;
        }

        // 贿赂当前怪兽
        int p2 = -1;
        if (money >= p[index] && dp[index - 1][money - p[index]] != -1) {
          p2 = dp[index - 1][money - p[index]] + d[index];
        }

        dp[index][money] = Math.max(p1, p2);
      }
    }

    for (int money = 0; money <= allMoney; money++) {
      if (dp[d.length - 1][money] != -1) {
        return money;
      }
    }

    return 0;
  }

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
      int ans1 = function1(d, p);
      int ans2 = function2(d, p);
      int ans3 = function3(d, p);
      int ans4 = function4(d, p);
      if (ans1 != ans2 && ans1 != ans3 && ans1 != ans4) {
        System.out.println("oops!");
      }
    }

  }
}
