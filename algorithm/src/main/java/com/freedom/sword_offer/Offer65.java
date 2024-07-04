package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/07 21:48
 */
public class Offer65 {

    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
