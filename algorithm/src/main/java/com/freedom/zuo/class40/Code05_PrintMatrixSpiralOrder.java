package com.freedom.zuo.class40;

/**
 * 给定一个长方形矩阵matrix，实现转圈打印
 * <p>
 * a b c d
 * <p>
 * e f g h
 * <p>
 * i j k L
 * <p>
 * 打印顺序：a b c d h L k j I e f g
 */
public class Code05_PrintMatrixSpiralOrder {


    public static void spiralOrderPrint(int[][] matrix) {
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            printEdge(matrix, startRow++, startCol++, endRow--, endCol--);
        }
    }

    public static void printEdge(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            for (int i = startCol; i <= endCol; i++) {
                System.out.print(matrix[startRow][i] + " ");
            }
        } else if (startCol == endCol) {
            for (int i = startRow; i < endRow; i++) {
                System.out.print(matrix[i][startCol] + " ");
            }
        } else {
            int curRow = startRow;
            int curCol = startCol;
            while (curCol < endCol) {
                System.out.print(matrix[startRow][curCol] + " ");
                curCol++;
            }
            while (curRow < endRow) {
                System.out.print(matrix[curRow][endCol] + " ");
                curRow++;
            }
            while (curCol > startCol) {
                System.out.print(matrix[endRow][curCol] + " ");
                curCol--;
            }
            while (curRow > startRow) {
                System.out.print(matrix[curRow][startCol] + " ");
                curRow--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(matrix);

    }

}
