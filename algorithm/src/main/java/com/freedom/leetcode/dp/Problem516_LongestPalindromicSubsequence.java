package com.freedom.leetcode.dp;

/**
 * 516. Longest Palindromic Subsequence
 */
public class Problem516_LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        return recursive(s.toCharArray(), 0, s.length() - 1);
    }

    private static int recursive(char[] chars, int i, int j) {
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }
        int max = 0;
        if (chars[i] == chars[j]) {
            int p1 = 2 + recursive(chars, i + 1, j - 1);
            max = Math.max(max, p1);
        } else {
            int p2 = recursive(chars, i + 1, j);
            int p3 = recursive(chars, i, j - 1);
            max = Math.max(max, p2);
            max = Math.max(max, p3);
        }
        return max;
    }

    /**
     * time complexity is O(n^2) ,space complexity is (n^2)
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            char aChar = chars[i];
            for (int j = i + 1; j < n; j++) {
                if (aChar == chars[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq2(s));
    }
}
