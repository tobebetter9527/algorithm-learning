package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. Combinations
 *
 * @author tobebetter9527
 * @create 2022/11/13 20:43
 */
public class Problem77_Combinations {





  public static List<List<Integer>> combine(int n, int k) {
    if (n < 1) {
      return new ArrayList<>();
    }

    List<List<Integer>> ansList = new LinkedList<>();
    process(ansList, new ArrayList<>(), 0, n, k);
    return ansList;
  }

  private static void process(List<List<Integer>> ansList, List<Integer> list, int pre, int n, int restK) {
    if (restK < 0) {
      return;
    }
    if (restK == 0) {
      ansList.add(list);
      return;
    }
    for (int i = pre + 1; i <= n; i++) {
      List<Integer> list2 = new ArrayList<>();
      list2.addAll(list);
      list2.add(i);
      process(ansList, list2, i, n, restK - 1);
    }
  }

  public static void main(String[] args) {
    int n = 1;
    int k = 1;
    List<List<Integer>> combine = combine(n, k);
    System.out.println(combine);
  }
}
