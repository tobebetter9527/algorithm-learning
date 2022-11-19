package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 51. N-Queens
 * <p>
 * 优化版本
 *
 * @author tobebetter9527
 * @create 2022/11/19 12:51
 */
public class Problem51_NQueens1 {

  List<List<String>> ans = new LinkedList<>();
  int[] path;
  public List<List<String>> solveNQueens(int n) {
    path = new int[n];
    backTracking(n, 0);
    return ans;
  }

  private List<String> processStrs(int n, int[] path) {
    List<String> result = new ArrayList<>(n);
    StringBuilder sb = new StringBuilder();
    for (Integer num : path) {
      for (int i = 0; i < n; i++) {
        if (num == i) {
          sb.append("Q");
        } else {
          sb.append(".");
        }
      }
      result.add(sb.toString());
      sb.delete(0, sb.length());
    }
    return result;
  }

  private void backTracking(int n, int row) {
    if (row == n) {
      ans.add(processStrs(n, path));
      return;
    }
    for (int col = 0; col < n; col++) {
      if (isValid(path, row, col)) {
        path[row] = col;
        backTracking(n, row + 1);
      }
    }
  }

  private boolean isValid(int[] path, int row, int col) {
    for (int j = 0; j < row; j++) {
      if (col == path[j] || Math.abs(row - j) == Math.abs(col - path[j])) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int n = 4;
    Problem51_NQueens1 problem = new Problem51_NQueens1();
    List<List<String>> lists = problem.solveNQueens(n);
    System.out.println(lists);
  }

}
