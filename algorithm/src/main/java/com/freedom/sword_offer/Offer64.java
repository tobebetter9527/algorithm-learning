package com.freedom.sword_offer;

public class Offer64 {

    public static int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(sumNums(n));
    }
}
