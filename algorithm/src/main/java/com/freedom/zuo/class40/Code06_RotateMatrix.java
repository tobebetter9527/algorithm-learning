package com.freedom.zuo.class40;

/**
 * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子 a b c | g d a
 * <p>
 * d e f | h e b
 * <p>
 * g h i | i f c
 *
 * @author tobebetter9527
 * @create 2022/08/22 21:32
 */
public class Code06_RotateMatrix {

    public static void rotate(int[][] matrix) {
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix.length - 1;

        while (startRow < endRow) {
            rotateEdge(matrix, startRow++, startCol++, endRow--, endCol--);
        }
    }

    private static void rotateEdge(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        // 有多少组
        int temp;
        int n = endCol - startCol;
        for (int i = 0; i < n; i++) {
            temp = matrix[startRow][startCol + i];
            matrix[startRow][startCol + i] = matrix[endRow - i][startCol];
            matrix[endRow - i][startCol] = matrix[endRow][endCol - i];
            matrix[endRow][endCol - i] = matrix[startRow + i][endCol];
            matrix[startRow + i][endCol] = temp;
        }
    }

    // -----------------------------------------  //

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
