package com.freedom.leetcode.dp;

/**
 * 474. Ones and Zeroes
 *
 * @author tobebetter9527
 * @create 2022/12/04 19:32
 */
public class Problem474_OnesAndZeroes {

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] arrs = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int oneCount = 0, zeroCount = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '1') {
                    oneCount++;
                } else {
                    zeroCount++;
                }
            }
            int[] arr = new int[2];
            arr[0] = zeroCount;
            arr[1] = oneCount;
            arrs[i] = arr;
        }

        return recursive(arrs, 0, m, n);
    }

    /**
     * @param arrs
     * @param index 来到的索引
     * @param restM 剩余0的数量
     * @param restN 剩余1的数量
     * @return 集合的大小
     */
    private static int recursive(int[][] arrs, int index, int restM, int restN) {
        if (index == arrs.length) {
            return 0;
        }
        // 不取当前值
        int p1 = recursive(arrs, index + 1, restM, restN);
        // 取当前值
        int p2 = 0;
        if (restM >= arrs[index][0] && restN >= arrs[index][1]) {
            p2 = 1 + recursive(arrs, index + 1, restM - arrs[index][0], restN - arrs[index][1]);
        }

        return Math.max(p1, p2);
    }


    public static int findMaxForm2(String[] strs, int m, int n) {
        int[][] arrs = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int oneCount = 0, zeroCount = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '1') {
                    oneCount++;
                } else {
                    zeroCount++;
                }
            }
            int[] arr = new int[2];
            arr[0] = zeroCount;
            arr[1] = oneCount;
            arrs[i] = arr;
        }

        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int index = strs.length - 1; index >= 0; index--) {
            for (int restM = 0; restM <= m; restM++) {
                for (int restN = 0; restN <= n; restN++) {
                    // 不取当前值
                    int p1 = dp[index + 1][restM][restN];
                    // 取当前值
                    int p2 = 0;
                    if (restM >= arrs[index][0] && restN >= arrs[index][1]) {
                        p2 = 1 + dp[index + 1][restM - arrs[index][0]][restN - arrs[index][1]];
                    }

                    dp[index][restM][restN] = Math.max(p1, p2);
                }
            }
        }

        return dp[0][m][n];
    }

    public static int findMaxForm3(String[] strs, int m, int n) {
        int[][] arrs = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int oneCount = 0, zeroCount = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '1') {
                    oneCount++;
                } else {
                    zeroCount++;
                }
            }
            int[] arr = new int[2];
            arr[0] = zeroCount;
            arr[1] = oneCount;
            arrs[i] = arr;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int index = strs.length - 1; index >= 0; index--) {
            for (int restM = m; restM >= arrs[index][0]; restM--) {
                for (int restN = n; restN >= arrs[index][1]; restN--) {
                    // 不取当前值
                    int p1 = dp[restM][restN];
                    // 取当前值
                    int p2 = 1 + dp[restM - arrs[index][0]][restN - arrs[index][1]];
                    dp[restM][restN] = Math.max(p1, p2);
                }
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(findMaxForm3(strs, m, n));
    }

}
