package com.freedom.sword_offer;

public class Offer13 {

  int m;
  int n;
  int k;
  boolean[][] visited;

  public int movingCount(int m, int n, int k) {
    this.m = m;
    this.n = n;
    this.k = k;
    this.visited = new boolean[m][n];
    return dfs(0, 0);
  }

  private int dfs(int row, int col) {
    // 越界，已经访问
    if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
      return 0;
    }
    // 标记为访问过
    visited[row][col] = true;
    if (isConfirmed(row, col)) {
      return 0;
    }

    int sum = 0;
    sum += dfs(row + 1, col);
    sum += dfs(row - 1, col);
    sum += dfs(row, col + 1);
    sum += dfs(row, col - 1);

    return sum + 1;
  }

  private boolean isConfirmed(int row, int i) {
    return calculate(row) + calculate(i) > k;
  }

  public static int calculate(int num) {
    int sum = 0;
    while (num > 0) {
      sum += (num % 10);
      num /= 10;
    }
    return sum;
  }

  public static void main(String[] args) {
    int m = 2, n = 3, k = 1;
    Offer13 offer13 = new Offer13();
    int i = offer13.movingCount(m, n, k);
    System.out.println(i);
  }
}



