package com.freedom.leetcode.dp;

/**
 * 72. Edit Distance
 *
 * @author tobebetter9527
 * @create 2022/12/12 20:57
 */
public class Problem72_EditDistance {

  public static int minDistance(String word1, String word2) {
    if (word1.length() == 0) {
      return word2.length();
    }
    if (word2.length() == 0) {
      return word1.length();
    }
    return recursive(word1.toCharArray(), word2.toCharArray(), 0, 0);
  }

  private static int recursive(char[] chars1, char[] chars2, int index1, int index2) {
    if (index1 == chars1.length && index2 == chars2.length) {
      return 0;
    } else if (index1 == chars1.length && index2 < chars2.length) {
      return chars2.length - index2;
    } else if (index1 < chars1.length && index2 == chars2.length) {
      return chars1.length - index1;
    }

    int min = Integer.MAX_VALUE;
    if (chars1[index1] == chars2[index2]) {
      int p1 = recursive(chars1, chars2, index1 + 1, index2 + 1);
      min = Math.min(min, p1);
    } else {
      // chars1 delete a character
      int p1 = 1 + recursive(chars1, chars2, index1 + 1, index2);
      // chars1 insert a character
      int p2 = 1 + recursive(chars1, chars2, index1, index2 + 1);
      // chars1 replace a character
      int p3 = 1 + recursive(chars1, chars2, index1 + 1, index2 + 1);
      min = Math.min(min, p1);
      min = Math.min(min, p2);
      min = Math.min(min, p3);
    }
    return min;
  }

  /**
   * time complexity is O(n*m),space complexity is O(n*m)
   *
   * @param word1
   * @param word2
   * @return
   */
  public static int minDistance2(String word1, String word2) {
    if (word1.length() == 0) {
      return word2.length();
    }
    if (word2.length() == 0) {
      return word1.length();
    }
    char[] chars1 = word1.toCharArray();
    char[] chars2 = word2.toCharArray();
    int n = chars1.length;
    int m = chars2.length;
    int[][] dp = new int[n + 1][m + 1];
    dp[0][0] = 0;
    for (int col = 0; col < m; col++) {
      dp[n][col] = m - col;
    }
    for (int row = 0; row < n; row++) {
      dp[row][m] = n - row;
    }

    // 从下到上，从右到左
    for (int index1 = n - 1; index1 >= 0; index1--) {
      char c = chars1[index1];
      for (int index2 = m - 1; index2 >= 0; index2--) {
        if (c == chars2[index2]) {
          dp[index1][index2] = dp[index1 + 1][index2 + 1];
        } else {
          // chars1 delete a character
          int p1 = 1 + dp[index1 + 1][index2];
          // chars1 insert a character
          int p2 = 1 + dp[index1][index2 + 1];
          // chars1 replace a character
          int p3 = 1 + dp[index1 + 1][index2 + 1];
          dp[index1][index2] = Math.min(p1, Math.min(p2, p3));
        }
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    String word1 = "intention", word2 = "execution";
    System.out.println(minDistance2(word1, word2));
  }
}
