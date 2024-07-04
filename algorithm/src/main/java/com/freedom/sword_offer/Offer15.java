package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/05 10:03
 */
public class Offer15 {

    public static int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 120;
        System.out.println(hammingWeight(n));

    }
}
