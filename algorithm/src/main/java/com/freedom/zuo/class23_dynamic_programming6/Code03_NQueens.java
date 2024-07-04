package com.freedom.zuo.class23_dynamic_programming6;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * <p>
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * <p>
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * <p>
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * <p>
 * n=8，返回92
 *
 * @author tobebetter9527
 * @create 2022/07/23 20:36
 */
public class Code03_NQueens {

    /**
     * 暴力递归
     *
     * @param n n行
     * @return 有多少种放法
     */
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    /**
     * 每行仅放一个皇后，可以简化问题
     *
     * @param row    当前在哪行
     * @param record 皇后记录，record[row] = col, row行，col列有个皇后
     * @param n      共n行
     */
    private static int process(int row, int[] record, int n) {
        // 执行到能放满皇后
        if (row == n) {
            return 1;
        }

        int ways = 0;
        // 从0列到n-1列
        for (int col = 0; col < n; col++) {
            if (isAvailable(record, row, col)) {
                record[row] = col;
                ways += process(row + 1, record, n);
            }
        }

        return ways;
    }

    private static boolean isAvailable(int[] record, int row, int col) {
        // 这里注意只比较0到row-1行的
        for (int i = 0; i < row; i++) {
            // 同列或同在一条斜线上
            if (record[i] == col || Math.abs(row - i) == Math.abs(col - record[i])) {
                return false;
            }
        }
        return true;
    }

    // ------------------------------------------ //

    /**
     * 暴力递归
     *
     * @param n n行
     * @return 有多少种放法
     */
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit      1的位置表示可以放皇后，如果n为7，limit为0..0 1 1 1 1 1 1 1
     * @param colLimit   列的限制，例子  0 0 0 1 0 0 0， 1的地方表示不能放皇后，已经放过
     * @param leftLimit  左下限制，例子  0 0 1 0 0 0 0，
     * @param rightLimit 右下限制，例子  0 0 0 0 1 0 0，
     */
    private static int process2(int limit, int colLimit, int leftLimit, int rightLimit) {
        // 表示已经填满了
        if (limit == colLimit) {
            return 1;
        }

        // 当前可以放皇后的位置
        int pos = limit & (~(colLimit | leftLimit | rightLimit));
        int mostRightOne = 0;
        int ans = 0;
        // 当前还有位置可以填
        while (pos != 0) {
            // 获得最右的1
            mostRightOne = pos & (~pos + 1);

            // 最右的1放皇后
            pos = pos - mostRightOne;

            // 下个皇后的限制
            ans += process2(limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1,
                    (rightLimit | mostRightOne) >>> 1);
        }

        return ans;
    }


    public static void main(String[] args) {
        int n = 7;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
