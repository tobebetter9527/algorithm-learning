package com.freedom.leetcode.dp;

/**
 * 583. Delete Operation for Two Strings
 *
 * @author tobebetter9527
 * @create 2022/12/12 20:02
 */
public class Problem583_DeleteOperationForTwoStrings {

  public static int minDistance(String word1, String word2) {
    return recursive(word1.toCharArray(), word2.toCharArray(), 0, 0);
  }

  private static int recursive(char[] word1, char[] word2, int idx1, int idx2) {
    if (idx1 == word1.length && idx2 == word2.length) {
      return 0;
    } else if (idx1 == word1.length && idx2 < word2.length) {
      return word2.length - idx2;
    } else if (idx1 < word1.length && idx2 == word2.length) {
      return word1.length - idx1;
    }
    int min = Integer.MAX_VALUE;
    if (word1[idx1] == word2[idx2]) {
      int p1 = recursive(word1, word2, idx1 + 1, idx2 + 1);
      min = Math.min(min, p1);
    } else {
      int p2 = 1 + recursive(word1, word2, idx1 + 1, idx2);
      int p3 = 1 + recursive(word1, word2, idx1, idx2 + 1);
      min = Math.min(min, p2);
      min = Math.min(min, p3);
    }
    return min;
  }

  /**
   * time complexity is O(n * m), space complexity is O(n * m).
   *
   * @param word1
   * @param word2
   * @return
   */
  public static int minDistance2(String word1, String word2) {
    char[] char1 = word1.toCharArray();
    char[] char2 = word2.toCharArray();
    int n = char1.length;
    int m = char2.length;

    int[][] dp = new int[n + 1][m + 1];
    dp[0][0] = 0;
    for (int col = 0; col < m; col++) {
      dp[n][col] = m - col;
    }
    for (int row = 0; row < n; row++) {
      dp[row][m] = n - row;
    }
    // 从下到上，从右到左
    for (int idx1 = n - 1; idx1 >= 0; idx1--) {
      for (int idx2 = m - 1; idx2 >= 0; idx2--) {
        int min = Integer.MAX_VALUE;
        if (char1[idx1] == char2[idx2]) {
          int p1 = dp[idx1 + 1][idx2 + 1];
          min = Math.min(min, p1);
        } else {
          int p2 = 1 + dp[idx1 + 1][idx2];
          int p3 = 1 + dp[idx1][idx2 + 1];
          min = Math.min(min, p2);
          min = Math.min(min, p3);
        }
        dp[idx1][idx2] = min;
      }
    }
    return dp[0][0];
  }

  public static int minDistance3(String word1, String word2) {
    char[] char1 = word1.toCharArray();
    char[] char2 = word2.toCharArray();
    int n = char1.length;
    int m = char2.length;

    int[][] dp = new int[n + 1][m + 1];
    dp[0][0] = 0;
    for (int col = 0; col < m; col++) {
      dp[n][col] = m - col;
    }
    for (int row = 0; row < n; row++) {
      dp[row][m] = n - row;
    }
    // 从下到上，从右到左
    for (int idx1 = n - 1; idx1 >= 0; idx1--) {
      char c = char1[idx1];
      for (int idx2 = m - 1; idx2 >= 0; idx2--) {
        if (c == char2[idx2]) {
          dp[idx1][idx2] = dp[idx1 + 1][idx2 + 1];
        } else {
          int p2 = 1 + dp[idx1 + 1][idx2];
          int p3 = 1 + dp[idx1][idx2 + 1];
          dp[idx1][idx2] = Math.min(p2, p3);
        }
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    String word1 = "leetcode";
    String word2 = "etco";
    System.out.println(minDistance3(word1, word2));
  }
}
