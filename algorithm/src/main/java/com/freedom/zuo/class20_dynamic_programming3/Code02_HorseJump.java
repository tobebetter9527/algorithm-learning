package com.freedom.zuo.class20_dynamic_programming3;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * <p>
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * <p>
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * <p>
 * 给你三个 参数 x，y，k
 * <p>
 * 返回“马”从(0,0)位置出发，必须走k步
 * <p>
 * 最后落在(x,y)上的方法数有多少种?
 *
 * @author tobebetter9527
 * @create 2022/07/17 15:15
 */
public class Code02_HorseJump {

  /**
   * 从(0,0）位置出发，到目标（a,b)有多少种走法
   *
   * @param a 目标位置，横轴
   * @param b 目标位置，纵轴
   * @param k 可以走k步
   * @return 右多少种走法
   */
  public static int jump(int a, int b, int k) {
    return process(0, 0, k, a, b);
  }

  private static int process(int x, int y, int restK, int a, int b) {
    // 处理越界问题
    if (x < 0 || x >= 9 || y < 0 || y >= 10) {
      return 0;
    }

    // 步数为零，到达目标位置
    if (restK == 0) {
      return (x == a && y == b) ? 1 : 0;
    }

    // 其他八种情况
    int ways = process(x + 1, y + 2, restK - 1, a, b);
    ways += process(x + 2, y + 1, restK - 1, a, b);
    ways += process(x + 2, y - 1, restK - 1, a, b);
    ways += process(x + 1, y - 2, restK - 1, a, b);
    ways += process(x - 1, y - 2, restK - 1, a, b);
    ways += process(x - 2, y - 1, restK - 1, a, b);
    ways += process(x - 2, y + 1, restK - 1, a, b);
    ways += process(x - 1, y + 2, restK - 1, a, b);

    return ways;
  }

  // --------------------------------------------------------//

  /**
   * 从(0,0）位置出发，到目标（a,b)有多少种走法. 动态规划
   *
   * @param a 目标位置，横轴
   * @param b 目标位置，纵轴
   * @param k 可以走k步
   * @return 右多少种走法
   */
  public static int jump2(int a, int b, int k) {
    int[][][] dp = new int[9][10][k + 1];

    // 步数为零，是否到达目标位置
    dp[a][b][0] = 1;

    // z轴，从下到上，第0层已经填过
    for (int z = 1; z <= k; z++) {
      for (int x = 0; x < 9; x++) {
        for (int y = 0; y < 10; y++) {
          int ways = pick(dp, x + 1, y + 2, z - 1);
          ways += pick(dp, x + 2, y + 1, z - 1);
          ways += pick(dp, x + 2, y - 1, z - 1);
          ways += pick(dp, x + 1, y - 2, z - 1);
          ways += pick(dp, x - 1, y - 2, z - 1);
          ways += pick(dp, x - 2, y - 1, z - 1);
          ways += pick(dp, x - 2, y + 1, z - 1);
          ways += pick(dp, x - 1, y + 2, z - 1);

          dp[x][y][z] = ways;
        }
      }
    }

    return dp[0][0][k];
  }

  public static int pick(int[][][] dp, int x, int y, int k) {
    // 处理越界问题
    if (x < 0 || x >= 9 || y < 0 || y >= 10) {
      return 0;
    }
    return dp[x][y][k];
  }

  public static void main(String[] args) {
    int x = 7;
    int y = 7;
    int step = 10;
    System.out.println(jump(x, y, step));
    System.out.println(jump2(x, y, step));
  }

}
