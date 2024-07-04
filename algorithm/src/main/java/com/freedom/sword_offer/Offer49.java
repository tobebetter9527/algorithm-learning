package com.freedom.sword_offer;

public class Offer49 {

    public static int nthUglyNumber(int n) {
        int[] ans = new int[n];
        ans[0] = 1;
        int a = 0, b = 0, c = 0;
        int x2 = 0, x3 = 0, x5 = 0;
        for (int i = 1; i < n; i++) {
            x2 = ans[a] * 2;
            x3 = ans[b] * 3;
            x5 = ans[c] * 5;
            ans[i] = Math.min(Math.min(x2, x3), x5);
            if (x2 == ans[i]) {
                a++;
            }
            if (x3 == ans[i]) {
                b++;
            }
            if (x5 == ans[i]) {
                c++;
            }
        }
        return ans[n - 1];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(nthUglyNumber(n));
    }
}
