package com.freedom.leetcode.dp;

/**
 * 746. Min Cost Climbing Stairs
 *
 * @author tobebetter9527
 * @create 2022/12/01 21:25
 */
public class Problem746_MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] arr = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Problem746_MinCostClimbingStairs problem = new Problem746_MinCostClimbingStairs();
        int i = problem.minCostClimbingStairs(arr);
        System.out.println(i);
    }

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(process(cost, 0, cost.length), process(cost, 1, cost.length));
    }

    private int process(int[] cost, int i, int n) {
        if (i == n - 1) {
            return cost[i];
        }
        if (i >= n) {
            return 0;
        }
        // 跳一步
        int p1 = cost[i] + process(cost, i + 1, n);
        // 跳两步
        int p2 = cost[i] + process(cost, i + 2, n);
        return Math.min(p1, p2);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = cost[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int p1 = cost[i] + dp[i + 1];
            int p2 = cost[i] + dp[i + 2];
            dp[i] = Math.min(p1, p2);
        }
        return Math.min(dp[0], dp[1]);
    }
}
