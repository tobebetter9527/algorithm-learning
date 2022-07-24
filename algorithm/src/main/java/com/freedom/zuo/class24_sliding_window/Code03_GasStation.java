package com.freedom.zuo.class24_sliding_window;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 加油站的良好出发点问题
 * <p>
 * 测试链接：https://leetcode.com/problems/gas-station
 *
 * @author tobebetter9527
 * @create 2022/07/24 15:57
 */
public class Code03_GasStation {

  /**
   * 加油站的良好出发点问题
   *
   * @param gas  当前位置index，油量gas[index]
   * @param cost 当前位置index，到下个index + 1位置，耗油cost[index]
   * @return 哪个点出发，可以绕一圈回来
   */
  public static int canCompleteCircuit(int[] gas, int[] cost) {
    boolean[] good = goodArray(gas, cost);
    for (int i = 0; i < gas.length; i++) {
      if (good[i]) {
        return i;
      }
    }
    return -1;
  }

  private static boolean[] goodArray(int[] gas, int[] cost) {
    int n = gas.length;
    int doubleN = n << 1;

    // 构建双倍大小的数组
    int[] arr = new int[doubleN];
    for (int i = 0; i < n; i++) {
      arr[i] = gas[i] - cost[i];
      arr[i + n] = gas[i] - cost[i];
    }
    // 前缀和，表示点的耗油量
    for (int i = 1; i < doubleN; i++) {
      arr[i] += arr[i - 1];
    }

    // 构建滑动窗口，获取窗口内最小数值
    Deque<Integer> minWin = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      while (!minWin.isEmpty() && arr[minWin.peekLast()] >= arr[i]) {
        minWin.pollLast();
      }
      minWin.addLast(i);
    }

    boolean[] ans = new boolean[n];
    for (int offset = 0, index = 0, right = n; index < n; offset = arr[index++], right++) {
      // arr[minWin.peekFirst()] - offset还原原来的值（理解累加和），判断最小的值是否大于0，是，代表该点出发，可绕一圈
      if (arr[minWin.peekFirst()] - offset >= 0) {
        ans[index] = true;
      }

      // 窗口向右滑动一位
      if (minWin.peekFirst() == index) {
        minWin.pollFirst();
      }
			while (!minWin.isEmpty() && arr[minWin.peekLast()] >= arr[right]) {
				minWin.pollLast();
			}
			minWin.addLast(right);
    }

    return ans;
  }

  public static void main(String[] args) {
    int[] gas = {1, 1, 3, 1};
    int[] cost = {2, 1, 2, 1};
    int i = canCompleteCircuit(gas, cost);

  }

}
