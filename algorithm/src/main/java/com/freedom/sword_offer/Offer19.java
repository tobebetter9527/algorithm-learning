package com.freedom.sword_offer;

public class Offer19 {

    public static void main(String[] args) {
        String s = "aa";
        String p = "*a";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length() + 1;
        int n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // row = 0的行
        for (int j = 2; j < n; j++) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*' && j >= 2) {
                    // s[i-1]字符匹配0次
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                        // s[i-1]字符匹配1次, 此时要s[i-1]=p[j-2]
                    } else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) {
                        dp[i][j] = true;
                        // p[j-2] = '.'
                    } else if (dp[i - 1][j] && p.charAt(j - 2) == '.') {
                        dp[i][j] = true;
                    }
                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public static boolean isMatch2(String s, String p) {
        return recursive(s, p, s.length(), p.length());
    }

    /**
     * 字符串s从0到第i个，p从0到第j个的匹配结果
     */
    private static boolean recursive(String s, String p, int i, int j) {
        // 都没有匹配到，默认返回为true
        if (i == 0 && j == 0) {
            return true;
        }
        // 此时p要类似为".*"或"a*"
        if (i == 0 && j != 0) {
            return j >= 2 && recursive(s, p, i, j - 2) && p.charAt(j - 1) == '*';
        }
        // p为空，肯定匹配不上
        if (i != 0 && j == 0) {
            return false;
        }

        if (p.charAt(j - 1) == '*' && j >= 2) {
            // s[i-1]字符匹配0次
            if (recursive(s, p, i, j - 2)) {
                return true;
                // s[i-1]字符匹配1次, 此时要s[i-1]=p[j-2]
            } else if (recursive(s, p, i - 1, j) && s.charAt(i - 1) == p.charAt(j - 2)) {
                return true;
                // p[j-2] = '.'
            } else if (recursive(s, p, i - 1, j) && p.charAt(j - 2) == '.') {
                return true;
            } else {
                return false;
            }
        } else if (p.charAt(j - 1) == '.') {
            return recursive(s, p, i - 1, j - 1);
        } else {
            return recursive(s, p, i - 1, j - 1) && s.charAt(i - 1) == p.charAt(j - 1);
        }
    }


}
