package com.freedom.leetcode.dp;

/**
 * 96. Unique Binary Search Trees
 *
 * @author tobebetter9527
 * @create 2022/12/03 15:54
 */
public class Problem96_UniqueBinarySearchTrees {


  public static int numTrees(int n) {
    return recursive(1, n);
  }

  private static int recursive(int startVal, int restN) {
    if (startVal >= restN) {
      return 1;
    }

    int way = 0;
    // 当前的root value作root节点的值
    for (int root = startVal; root <= restN; root++) {
      // 左子树和右子树的种类相乘
      way += recursive(startVal, root - 1) * recursive(root + 1, restN);
    }

    return way;
  }

  /**
   * time complexity is O(n^3),space complexity is O(n^2)
   *
   * @param n
   * @return
   */
  public static int numTrees2(int n) {
    int[][] dp = new int[n + 1][n + 1];
    // 从下到上，从左到右
    for (int row = n; row >= 0; row--) {
      for (int col = 0; col <= row; col++) {
        dp[row][col] = 1;
      }
    }

    for (int row = n - 1; row > 0; row--) {
      for (int col = row + 1; col <= n; col++) {
        int way = 0;
        for (int root = row; root <= col; root++) {
          // 左子树和右子树的种类相乘
          way += dp[row][root - 1] * (root + 1 <= n ? dp[root + 1][col] : 1);
        }
        dp[row][col] = way;
      }
    }

    return dp[1][n];
  }

  /**
   * 另外的思路，递推公式：dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
   *
   * @param n
   * @return
   */
  public static int numTrees3(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    int n = 3;
    int i = numTrees3(n);
    System.out.println(i);
  }

}
