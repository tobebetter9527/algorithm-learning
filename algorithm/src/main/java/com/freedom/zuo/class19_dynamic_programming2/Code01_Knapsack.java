package com.freedom.zuo.class19_dynamic_programming2;

/**
 * 背包问题
 * <p>
 * 给定两个长度都为N的数组weights和values，
 * <p>
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * <p>
 * 给定一个正数bag，表示一个载重bag的袋子，
 * <p>
 * 你装的物品不能超过这个重量。
 * <p>
 * 返回你能装下最多的价值是多少?
 *
 * @author tobebetter9527
 * @create 2022/07/16 10:27
 */
public class Code01_Knapsack {

  /**
   * 暴力递归，求最大值
   *
   * @param weights 重量
   * @param values  价值
   * @param bag     背包大小
   * @return 能装货物的最大价值
   */
  public static int maxValue(int[] weights, int[] values, int bag) {
    if (weights == null || values == null || weights.length == 0 || weights.length != values.length) {
      return 0;
    }
    return process(weights, values, 0, bag);
  }

  /**
   * @param weights 重量
   * @param values  价值
   * @param index   来到当前索引位置
   * @param restBag 剩余背包能装多少重量
   */
  private static int process(int[] weights, int[] values, int index, int restBag) {
    // 如果背包剩余重量为负数
    if (restBag < 0) {
      return -1;
    }
    // 如果index到了边界，无东西可取，返回0
    if (index == weights.length) {
      return 0;
    }

    // 当前index的货不要
    int p1 = process(weights, values, index + 1, restBag);

    // 当前index的货要
    int p2 = 0;
    int next = process(weights, values, index + 1, restBag - weights[index]);
    // 表明背包可以装
    if (next != -1) {
      p2 = values[index] + next;
    }

    return Math.max(p1, p2);
  }

  // ------------------------------------------------------------- //

  /**
   * 缓存
   *
   * @param weights
   * @param values
   * @param bag
   */
  public static int maxValue2(int[] weights, int[] values, int bag) {
    if (weights == null || values == null || weights.length == 0 || weights.length != values.length) {
      return 0;
    }

    int[][] result = new int[weights.length + 1][bag + 1];
    for (int i = 0; i <= weights.length; i++) {
      for (int j = 0; j <= bag; j++) {
        result[i][j] = -1;
      }
    }

    process2(weights, values, 0, bag, result);
    return result[0][bag];
  }

  /**
   * @param weights 重量
   * @param values  价值
   * @param index   来到当前索引位置
   * @param restBag 剩余背包能装多少重量
   */
  private static int process2(int[] weights, int[] values, int index, int restBag, int[][] result) {
    // 如果背包剩余重量为负数
    if (restBag < 0) {
      return -1;
    }
    if (result[index][restBag] != -1) {
      return result[index][restBag];
    }

    int ans;
    // 如果index到了边界，无东西可取，返回0
    if (index == weights.length) {
      ans = 0;
    } else {
      // 当前index的货不要
      int p1 = process2(weights, values, index + 1, restBag, result);

      // 当前index的货要
      int p2 = 0;
      int next = process2(weights, values, index + 1, restBag - weights[index], result);
      // 表明背包可以装
      if (next != -1) {
        p2 = values[index] + next;
      }

      ans = Math.max(p1, p2);
    }

    result[index][restBag] = ans;

    return ans;
  }

  // ------------------------------------------------------------- //

  /**
   * 动态规划
   *
   * @param weights
   * @param values
   * @param bag
   */
  public static int dp(int[] weights, int[] values, int bag) {
    if (weights == null || values == null || weights.length == 0 || weights.length != values.length) {
      return 0;
    }

    int[][] result = new int[weights.length + 1][bag + 1];

    // 最后一行都是0
    for (int i = weights.length - 1; i >= 0; i--) {
      for (int restBag = 0; restBag <= bag; restBag++) {
        int p1 = result[i + 1][restBag];

        int next = restBag - weights[i] < 0 ? -1 : result[i + 1][restBag - weights[i]];
        int p2 = 0;
        if (next != -1) {
          p2 = values[i] + next;
        }

        result[i][restBag] = Math.max(p1, p2);
      }
    }

    return result[0][bag];
  }

  public static void main(String[] args) {
    int[] weights = {3, 2, 4, 7, 3, 1, 7};
    int[] values = {5, 6, 3, 19, 12, 4, 2};
    int bag = 15;
    System.out.println(maxValue(weights, values, bag));
    System.out.println(maxValue2(weights, values, bag));
    System.out.println(dp(weights, values, bag));
  }

}
