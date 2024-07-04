package com.freedom.sword_offer;

import java.util.Arrays;

public class Offer60 {
    public static double[] dicesProbability(int n) {
        // n等于1的时候
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        // n从2开始遍历
        for (int i = 2; i <= n; i++) {
            // 临时数组
            double[] temp = new double[i * 5 + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            dp = temp;
        }
        return dp;
    }

    public static void main(String[] args) {
        int n = 2;
        double[] dp = dicesProbability(2);
        for (double v : dp) {
            System.out.print(v + " ");
        }
    }
}
