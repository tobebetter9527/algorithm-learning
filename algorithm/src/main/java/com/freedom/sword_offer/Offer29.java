package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/15 20:45
 */
public class Offer29 {

  public static int[] spiralOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return new int[0];
    }

    int m = matrix.length, n = matrix[0].length;
    int[] ans = new int[m * n];
    int idx = 0;

    int startRow = 0, startCol = 0, endRow = m - 1, endCol = n - 1;
    while (startRow <= endRow && startCol <= endCol) {
      if (startRow == endRow) {
        for (int i = startCol; i <= endCol; i++) {
          ans[idx++] = matrix[startRow][i];
        }
      } else if (startCol == endCol) {
        for (int i = startRow; i <= endRow; i++) {
          ans[idx++] = matrix[i][startCol];
        }
      } else {
        for (int i = startCol; i < endCol; i++) {
          ans[idx++] = matrix[startRow][i];
        }
        for (int i = startRow; i < endRow; i++) {
          ans[idx++] = matrix[i][endCol];
        }
        for (int i = endCol; i > startCol; i--) {
          ans[idx++] = matrix[endRow][i];
        }
        for (int i = endRow; i > startRow; i--) {
          ans[idx++] = matrix[i][startCol];
        }
      }
      startRow++;
      startCol++;
      endRow--;
      endCol--;
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
    int[] ints = spiralOrder(matrix);
    for (int anInt : ints) {
      System.out.print(anInt + " ");
    }
  }
}
