package com.freedom.leetcode.dp;

/**
 * 115. Distinct Subsequences
 */
public class Problem115_DistinctSubsequences {

  public int numDistinct(String s, String t) {
    if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
      return 0;
    }
    return recursive(s, t, 0, 0);
  }

  private int recursive(String s, String t, int idxs, int idxt) {
    if (idxt == t.length()) {
      return 1;
    }
    if (idxs == s.length()) {
      return 0;
    }
    int ways = 0;
    if (s.charAt(idxs) == t.charAt(idxt)) {
      ways += recursive(s, t, idxs + 1, idxt);
      ways += recursive(s, t, idxs + 1, idxt + 1);
    } else {
      ways += recursive(s, t, idxs + 1, idxt);
    }
    return ways;
  }

  /**
   * time complexity is O(n*m), space complexity is O(n*m)
   *
   * @param s
   * @param t
   * @return
   */
  public int numDistinct2(String s, String t) {
    if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
      return 0;
    }
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
//    for (int col = 0; col < m; col++) {
//      dp[n][col] = 0;
//    }
    for (int row = 0; row <= n; row++) {
      dp[row][m] = 1;
    }

    // 从下到上，从左到右
    for (int idxs = n - 1; idxs >= 0; idxs--) {
      for (int idxt = 0; idxt < m; idxt++) {
        int ways = 0;
        if (s.charAt(idxs) == t.charAt(idxt)) {
          ways += dp[idxs + 1][idxt];
          ways += dp[idxs + 1][idxt + 1];
        } else {
          ways += dp[idxs + 1][idxt];
        }
        dp[idxs][idxt] = ways;
      }
    }
    return dp[0][0];
  }

  public int numDistinct3(String s, String t) {
    if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
      return 0;
    }
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int row = 0; row <= n; row++) {
      dp[row][m] = 1;
    }

    // 从下到上，从左到右
    for (int idxs = n - 1; idxs >= 0; idxs--) {
      for (int idxt = 0; idxt < m; idxt++) {
        dp[idxs][idxt] = dp[idxs + 1][idxt];
        if (s.charAt(idxs) == t.charAt(idxt)) {
          dp[idxs][idxt] += dp[idxs + 1][idxt + 1];
        }
      }
    }
    return dp[0][0];
  }

  /**
   * time complexity is O(n*m), space complexity is O(m)
   *
   * @param s
   * @param t
   * @return
   */
  public int numDistinct4(String s, String t) {
    if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
      return 0;
    }
    int n = s.length();
    int m = t.length();
    int[] dp = new int[m + 1];
    dp[m] = 1;

    // 从下到上，从左到右
    for (int idxs = n - 1; idxs >= 0; idxs--) {
      for (int idxt = 0; idxt < m; idxt++) {
        if (s.charAt(idxs) == t.charAt(idxt)) {
          dp[idxt] += dp[idxt + 1];
        }
      }
    }
    return dp[0];
  }

}
