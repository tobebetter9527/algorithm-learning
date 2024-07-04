package com.freedom.leetcode.array;

/**
 * Spiral Matrix II
 *
 * @author tobebetter9527
 * @create 2022/10/15 9:43
 */
public class Problem59_SpiralMatrixII {


    public static int[][] generateMatrix(int n) {
        if (n < 1) {
            return null;
        }
        int[][] ans = new int[n][n];
        int start = 0, end = n - 1, value = 1;
        while (start < end) {
            for (int i = start; i < end; i++) {
                ans[start][i] = value++;
            }
            for (int i = start; i < end; i++) {
                ans[i][end] = value++;
            }
            for (int i = end; i > start; i--) {
                ans[end][i] = value++;
            }
            for (int i = end; i > start; i--) {
                ans[i][start] = value++;
            }
            start++;
            end--;
        }

        // n为奇数的时候
        if ((n & 1) == 1) {
            ans[start][start] = value++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 1;
        int[][] ints = generateMatrix(n);
        System.out.println(ints);


    }
}