package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 51. N-Queens
 *
 * @author tobebetter9527
 * @create 2022/11/19 12:51
 */
public class Problem51_NQueens {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        int n = 4;
        Problem51_NQueens problem = new Problem51_NQueens();
        List<List<String>> lists = problem.solveNQueens(n);
        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n) {
        backTracking(n, 0);
        List<List<String>> list = new ArrayList<>(ans.size());
        for (List<Integer> an : ans) {
            processStrs(n, list, an);
        }
        return list;
    }

    private void processStrs(int n, List<List<String>> list, List<Integer> an) {
        List<String> result = new ArrayList<>(n);
        for (Integer num : an) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (num == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }
        list.add(result);
    }

    private void backTracking(int n, int row) {
        if (path.size() == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(path, row, col)) {
                path.add(col);
                backTracking(n, row + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isValid(List<Integer> path, int row, int col) {
        if (!path.isEmpty()) {
            for (int j = 0; j < path.size(); j++) {
                if (col == path.get(j) || Math.abs(row - j) == Math.abs(col - path.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
