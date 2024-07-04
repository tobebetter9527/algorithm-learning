package com.freedom.zuo.class20_dynamic_programming3;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * <p>
 * 本题的另外解法： 可以字符串倒序，然后求原序的字符串和倒序的字符串的最大公共子序列
 *
 * @author tobebetter9527
 * @create 2022/07/17 11:23
 */
public class Code01_PalindromeSubsequence {

    /**
     * 求最大回文子序列
     * <p>
     * 暴力递归
     *
     * @param s
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process(s.toCharArray(), 0, s.length() - 1);
    }

    private int process(char[] str, int left, int right) {
        // 只剩下一个字符，肯定是回文
        if (left == right) {
            return 1;
        }
        // 如果剩下两个字符，相等就是两个，不等就是1一个
        if (left == right - 1) {
            return str[left] == str[right] ? 2 : 1;
        }

        // 情况1，取left，不取riht
        int p1 = process(str, left, right - 1);
        // 情况2，不取left，取right
        int p2 = process(str, left + 1, right);
        // 情况3，不取left，不取right
        int p3 = process(str, left + 1, right - 1);
        // 情况4 ，取left，取right
        int p4 = str[left] == str[right] ? (2 + process(str, left + 1, right - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // ----------------------------------------------- //

    /**
     * 求最大回文子序列
     * <p>
     * 动态规划
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];

        // 右下角为1
        dp[n - 1][n - 1] = 1;
        // 同时填对角线和次对角线
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        // 填其他位置，从下到上，从左到右
        for (int left = n - 3; left >= 0; left--) {
            for (int right = left + 2; right < n; right++) {
                // 情况1，取left，不取riht
                int p1 = dp[left][right - 1];
                // 情况2，不取left，取right
                int p2 = dp[left + 1][right];
                // 情况3，不取left，不取right
                int p3 = dp[left + 1][right - 1];
                // 情况4 ，取left，取right
                int p4 = str[left] == str[right] ? (2 + dp[left + 1][right - 1]) : 0;
                dp[left][right] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return dp[0][n - 1];
    }

    // ----------------------------------------------- //

    /**
     * 求最大回文子序列
     * <p>
     * 动态规划,优化版本
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];

        // 右下角为1
        dp[n - 1][n - 1] = 1;
        // 同时填对角线和次对角线
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        // 填其他位置，从下到上，从左到右
        for (int left = n - 3; left >= 0; left--) {
            for (int right = left + 2; right < n; right++) {
                // 情况1，取left，不取riht
                int p1 = dp[left][right - 1];
                // 情况2，不取left，取right
                int p2 = dp[left + 1][right];

                // 情况3，不取left，不取right, 需要画方格图分析，之前的点已经分析比较过，这个可以忽略,省一个比较
                // int p3 = dp[left + 1][right - 1];

                // 情况4 ，取left，取right
                int p4 = str[left] == str[right] ? (2 + dp[left + 1][right - 1]) : 0;
                dp[left][right] = Math.max(Math.max(p1, p2), p4);
            }
        }

        return dp[0][n - 1];
    }

}
