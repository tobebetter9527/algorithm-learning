package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 332. Reconstruct Itinerary
 *
 * @author tobebetter9527
 * @create 2022/11/19 8:45
 */
public class Problem332_ReconstructItinerary1 {

  List<String> ans = new LinkedList<>();
  LinkedList<String> path = new LinkedList<>();
  boolean[] used;

  public List<String> findItinerary(List<List<String>> tickets) {
    Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
    used = new boolean[tickets.size()];
    path.add("JFK");
    backTracking(tickets, tickets.size());
    return ans;
  }

  /**
   * 能用，但是超时
   *
   * @param tickets
   */
  private boolean backTracking(List<List<String>> tickets, int n) {
    if (path.size() == n + 1) {
      ans.addAll(path);
      return true;
    }

    for (int i = 0; i < n; i++) {
      // 用过就不能再使用
      if (used[i] || !tickets.get(i).get(0).equals(path.getLast())) {
        continue;
      }
      path.add(tickets.get(i).get(1));
      used[i] = true;

      if (backTracking(tickets, n)) {
        return true;
      }
      path.removeLast();
      used[i] = false;
    }
    return false;
  }

  public static void main(String[] args) {
    List<List<String>> tickets = new ArrayList<>();
    List<String> list1 = Arrays.asList("JFK", "SFO");
    List<String> list2 = Arrays.asList("JFK", "ATL");
    List<String> list3 = Arrays.asList("SFO", "ATL");
    List<String> list4 = Arrays.asList("ATL", "JFK");
    List<String> list5 = Arrays.asList("ATL", "SFO");
    tickets.add(list1);
    tickets.add(list2);
    tickets.add(list3);
    tickets.add(list4);
    tickets.add(list5);
    Problem332_ReconstructItinerary1 problem = new Problem332_ReconstructItinerary1();
    List<String> itinerary = problem.findItinerary(tickets);
    System.out.println(itinerary);

  }


}
