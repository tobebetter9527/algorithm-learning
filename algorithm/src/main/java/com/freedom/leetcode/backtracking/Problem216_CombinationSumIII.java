package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. Combination Sum III
 *
 * @author tobebetter9527
 * @create 2022/11/14 20:00
 */
public class Problem216_CombinationSumIII {

    static List<List<Integer>> result = new LinkedList<>();
    static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(1, 0, k, n);
        return result;
    }

    private static void backTracking(int start, int sum, int k, int n) {
        if (sum > n) {
            return;
        }
        if (path.size() == k) {
            if (sum == n) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backTracking(i + 1, sum, k, n);
            path.remove(path.size() - 1);
            sum -= i;
        }
    }


    public static List<List<Integer>> combinationSum4(int k, int n) {
        process(1, k, n);
        return result;
    }

    private static void process(int pre, int restK, int restN) {
        if (restK < 0 || restN < 0) {
            return;
        }
        if (restK == 0 && restN == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = pre; i <= 9; i++) {
            path.add(i);
            process(i + 1, restK - 1, restN - i);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        int k = 3, n = 7;

        List<List<Integer>> lists = combinationSum3(k, n);
        System.out.println(lists);
    }
}
