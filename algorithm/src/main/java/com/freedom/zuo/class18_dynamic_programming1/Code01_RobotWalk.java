package com.freedom.zuo.class18_dynamic_programming1;

/**
 * 机器人行走问题
 * <p>
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2 开始时机器人在其中的 start位置上(start 一定是 1~N 中的一个) 如果机器人来到1位置，
 * <p>
 * 那么下一步只能往右来到2位置； 如果机器人来到N位置，那么下一步只能往左来到 N-1位置；
 * <p>
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走； 规定机器人必须走 K 步，最终能来到aim位置(aim也是1~N中的一个)的方法有多少种
 * <p>
 * 给定四个参数 N、start、K、aim，返回方法数。
 *
 * @author tobebetter9527
 * @create 2022/07/14 21:56
 */
public class Code01_RobotWalk {

  /**
   * 暴力递归
   *
   * @param start 起始位置
   * @param k     可以走k步
   * @param aim   目标位置
   * @param n     总共有n个点
   */
  public static int way1(int start, int k, int aim, int n) {
    if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
      return -1;
    }

    return process1(start, k, aim, n);
  }

  /**
   * @param cur  当前位置
   * @param rest 剩余步数
   * @param aim  目标位置
   * @param n    总共有n个点
   */
  private static int process1(int cur, int rest, int aim, int n) {
    // 剩余步数为0, 如果cur来到目标点，说明找到一种方法，否则没有找到
    if (rest == 0) {
      return cur == aim ? 1 : 0;
    }
    // 碰到边界条件，只能往2走
    if (cur == 1) {
      return process1(cur + 1, rest - 1, aim, n);
      // 碰到边界条件，只能往n-1走
    } else if (cur == n) {
      return process1(cur - 1, rest - 1, aim, n);
      // 既可以往左走，也可以往右走
    } else {
      return process1(cur + 1, rest - 1, aim, n) + process1(cur - 1, rest - 1, aim, n);
    }
  }

  // --------------------------------------------------------------------------- //

  /**
   * 暴力递归, 有重复解决
   *
   * @param start 起始位置
   * @param k     可以走k步
   * @param aim   目标位置
   * @param n     总共有n个点
   */
  public static int way2(int start, int k, int aim, int n) {
    if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
      return -1;
    }

    int[][] dp = new int[n + 1][k + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= k; j++) {
        // 表示没有走过
        dp[i][j] = -1;
      }
    }

    return process2(start, k, aim, n, dp);
  }

  private static int process2(int cur, int rest, int aim, int n, int[][] dp) {
    if (dp[cur][rest] != -1) {
      return dp[cur][rest];
    }

    int ans;
    if (rest == 0) {
      ans = cur == aim ? 1 : 0;
    } else if (cur == 1) {
      ans = process2(cur + 1, rest - 1, aim, n, dp);
      // 碰到边界条件，只能往n-1走
    } else if (cur == n) {
      ans = process2(cur - 1, rest - 1, aim, n, dp);
      // 既可以往左走，也可以往右走
    } else {
      ans = process2(cur + 1, rest - 1, aim, n, dp) + process2(cur - 1, rest - 1, aim, n, dp);
    }
    dp[cur][rest] = ans;
    return ans;
  }

  // --------------------------------------------------------------------------- //

  /**
   * 暴力递归, 有重复解决: 上面的依赖左下解，中间依赖左下和左上，下面的依赖左上。
   *
   * @param start 起始位置
   * @param k     可以走k步
   * @param aim   目标位置
   * @param n     总共有n个点
   */
  public static int way3(int start, int k, int aim, int n) {
    if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
      return -1;
    }

    int[][] dp = new int[n + 1][k + 1];

    // 终止条件，有一个解,其他默认位置为0
    dp[aim][0] = 1;

    // 第0行不管，从上到下，从左到右，依次填写
    for (int col = 1; col <= k; col++) {
      // 第一行
      dp[1][col] = dp[2][col - 1];

      // 第2到第n-1行
      for (int row = 2; row < n; row++) {
        dp[row][col] = dp[row + 1][col - 1] + dp[row - 1][col - 1];
      }

      // 第n行
      dp[n][col] = dp[n - 1][col - 1];
    }

    return dp[start][k];
  }


  public static void main(String[] args) {
    int start = 2;
    int k = 6;
    int aim = 4;
    int n = 5;

    System.out.println(way1(start, k, aim, n));
    System.out.println(way2(start, k, aim, n));
    System.out.println(way3(start, k, aim, n));
  }

}
