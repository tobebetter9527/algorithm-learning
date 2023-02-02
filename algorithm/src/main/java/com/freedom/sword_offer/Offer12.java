package com.freedom.sword_offer;

public class Offer12 {

  public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;
    if (m * n < word.length()) {
      return false;
    }
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dfs(board, i, j, m, n, word, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int i, int j, int m, int n, String word, int idx, boolean[][] visited) {
    if (i < 0 || i == m || j < 0 || j == n || visited[i][j] || board[i][j] != word.charAt(idx)) {
      return false;
    }
    if (idx == word.length() - 1) {
      return true;
    }
    visited[i][j] = true;
    boolean flag = dfs(board, i + 1, j, m, n, word, idx + 1, visited) ||
        dfs(board, i - 1, j, m, n, word, idx + 1, visited) ||
        dfs(board, i, j + 1, m, n, word, idx + 1, visited) ||
        dfs(board, i, j - 1, m, n, word, idx + 1, visited);
    visited[i][j] = false;
    return flag;
  }

  public static void main(String[] args) {
    char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    String word = "ABAB";
    Offer12 offer12 = new Offer12();
    boolean exist = offer12.exist(board, word);
    System.out.println(exist);
  }


}
