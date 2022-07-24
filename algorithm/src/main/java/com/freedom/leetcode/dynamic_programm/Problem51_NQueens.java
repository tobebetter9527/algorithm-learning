package com.freedom.leetcode.dynamic_programm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/n-queens/
 *
 * @author tobebetter9527
 * @create 2022/07/23 23:57
 */
public class Problem51_NQueens {

  public static List<List<String>> solveNQueens(int n) {
    if (n < 1) {
      return null;
    }
    int[] record = new int[n];
    List<List<String>> result = new LinkedList<>();
    process(0, record, n, result);
    return result;

  }

  private static int process(int row, int[] record, int n, List<List<String>> result) {
    // 执行到能放满皇后
    if (row == n) {
      List<String> list = new ArrayList<>(n);
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < n; i++) {
        int index = record[i];
        for (int j = 0; j < n; j++) {
          if (j != index) {
            str.append(".");
          } else {
            str.append("Q");
          }
        }
        list.add(str.toString());
        str.delete(0, str.length());
      }
      result.add(list);

      return 1;
    }

    int ways = 0;
    // 从0列到n-1列
    for (int col = 0; col < n; col++) {
      if (isAvailable(record, row, col)) {
        record[row] = col;
        ways += process(row + 1, record, n, result);
      }
    }

    return ways;
  }

  private static boolean isAvailable(int[] record, int row, int col) {
    // 这里注意只比较0到row-1行的
    for (int i = 0; i < row; i++) {
      // 同列或同在一条斜线上
      if (record[i] == col || Math.abs(row - i) == Math.abs(col - record[i])) {
        return false;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    int n = 4;

    long start = System.currentTimeMillis();
    System.out.println(solveNQueens(n));
    long end = System.currentTimeMillis();
    System.out.println("cost time: " + (end - start) + "ms");

  }

}
