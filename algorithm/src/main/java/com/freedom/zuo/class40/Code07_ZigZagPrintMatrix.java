package com.freedom.zuo.class40;

/**
 * 给定一个正方形或者长方形矩阵matrix，实现zigzag打印
 * <p>
 * 0 1 2
 * <p>
 * 3 4 5
 * <p>
 * 6 7 8
 * <p>
 * 打印: 0 1 3 6 4 2 5 7 8
 *
 * @author tobebetter9527
 * @create 2022/08/22 22:09
 */
public class Code07_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        // (a,b)点，（c,d)点
        int a = 0, b = 0, c = 0, d = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        boolean flag = true;
        while (a <= endRow) {
            printLevel(matrix, a, b, c, d, flag);

            // (a,b)点 向左，再向下移动
            a = b == endCol ? a + 1 : a;
            b = b == endCol ? endCol : b + 1;

            // （c,d)点 向下，再向左移动
            d = c == endRow ? d + 1 : d;
            c = c == endRow ? endRow : c + 1;

            flag = !flag;
        }
    }

    private static void printLevel(int[][] matrix, int a, int b, int c, int d, boolean flag) {
        if (flag) {
            while (d <= b) {
                System.out.print(matrix[c--][d++] + " ");
            }
        } else {
            while (d <= b) {
                System.out.print(matrix[a++][b--] + " ");
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        printMatrixZigZag(matrix);
    }


}
