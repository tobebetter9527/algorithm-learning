package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 332. Reconstruct Itinerary
 *
 * @author tobebetter9527
 * @create 2022/11/19 8:45
 */
public class Problem332_ReconstructItinerary {

    List<String> ans = new LinkedList<>();
    LinkedList<String> path = new LinkedList<>();
    String str = "Z";
    boolean[] used;

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
        Problem332_ReconstructItinerary problem = new Problem332_ReconstructItinerary();
        List<String> itinerary = problem.findItinerary(tickets);
        System.out.println(itinerary);

    }

    public List<String> findItinerary(List<List<String>> tickets) {
        used = new boolean[tickets.size()];
        backTracking(tickets, tickets.size(), 0, "");
        return ans;
    }

    /**
     * 能用，但是超时
     *
     * @param tickets
     * @param n
     * @param curIndex
     * @param pre
     */
    private void backTracking(List<List<String>> tickets, int n, int curIndex, String pre) {
        if (curIndex == n) {
            ArrayList<String> strings = new ArrayList<>(path);
            strings.add(pre);
            String join = String.join("", strings);
            if (join.compareTo(str) < 0) {
                str = join;
                ans = strings;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            // 用过就不能再使用
            if (used[i]) {
                continue;
            }
            // 第一张票必须JFK开头
            List<String> ticket = tickets.get(i);
            if (curIndex == 0 && !ticket.get(0).equals("JFK")) {
                continue;
            }

            if (curIndex == 0 || pre.equals(ticket.get(0))) {
                path.add(ticket.get(0));
                used[i] = true;
                backTracking(tickets, n, curIndex + 1, ticket.get(1));
                path.removeLast();
                used[i] = false;
            }
        }
    }


}
