package com.freedom.leetcode.dp;

/**
 * 647. Palindromic Substrings
 */
public class Problem647_PalindromicSubstrings {

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxLen = 15;
        for (int i = 0; i < testTimes; i++) {
            String s = generateStr(maxLen);
            int i1 = countSubstrings(s);
            int i2 = countSubstrings2(s);
            int i3 = countSubstrings3(s);
            int i4 = countSubstrings4(s);
            if (i1 != i2 || i1 != i3 || i1 != i4) {
                System.out.println("wrong" + s);
            }
        }
        System.out.println("done");
    }

    private static String generateStr(int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = generateAlphabet();
        }
        return String.valueOf(chars);
    }

    private static char generateAlphabet() {
        return (char) ((int) (Math.random() * 13) + 97);
    }

    /**
     * time complexity is O(n^3), space complexity is O(1)
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromic(chars, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean isPalindromic(char[] chars, int i, int j) {
        while (i <= j && chars[i] == chars[j]) {
            i++;
            j--;
        }
        return i > j;
    }

    /**
     * time complexity is O(n^2), space complexity is O(1)
     *
     * @param s
     * @return
     */
    public static int countSubstrings2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += extend(chars, i, i, n);
            ans += extend(chars, i, i + 1, n);
        }
        return ans;
    }

    private static int extend(char[] chars, int i, int j, int n) {
        int ans = 0;
        while (i >= 0 && j < n && chars[i] == chars[j]) {
            i--;
            j++;
            ans++;
        }
        return ans;
    }

    /**
     * time complexity is O(n^2), space complexity is O(n^2)
     *
     * @param s
     * @return
     */
    public static int countSubstrings3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int ans = n;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    if (j - i == 1) {
                        ans++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {
                        ans++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return ans;
    }

    public static int countSubstrings4(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int ans = n;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j] && (j - i == 1 || dp[i + 1][j - 1])) {
                    ans++;
                    dp[i][j] = true;
                }
            }
        }
        return ans;
    }
}
