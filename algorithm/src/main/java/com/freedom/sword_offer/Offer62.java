package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.List;

public class Offer62 {

  /**
   * 这种方法不好
   *
   * @param n
   * @param m
   * @return
   */
  public static int lastRemaining(int n, int m) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(i);
    }
    return recursive(list, 0, m);
  }

  private static int recursive(List<Integer> list, int start, int m) {
    if (list.size() == 1) {
      return list.get(0);
    }
    int idx = (start + m - 1) % list.size();
    list.remove(idx);
    return recursive(list, idx, m);
  }

  /**
   * 上述方法的迭代版本，依然不好
   *
   * @param n
   * @param m
   * @return
   */
  public static int lastRemaining2(int n, int m) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(i);
    }
    int start = 0;
    while (n > 1) {
      start = (start + m - 1) % n;
      list.remove(start);
      n--;
    }
    return list.get(0);
  }


  public static int lastRemaining3(int n, int m) {
    if (n == 1) {
      return 0;
    }
    int idx = lastRemaining3(n - 1, m);
    return (idx + m) % n;
  }

  public static int lastRemaining4(int n, int m) {
    int idx = 0;
    for (int i = 2; i <= n; i++) {
      idx = (idx + m) % i;
    }
    return idx;
  }

  public static void main(String[] args) {
    int n = 5, m = 3;
    System.out.println(lastRemaining4(n, m));
  }
}
