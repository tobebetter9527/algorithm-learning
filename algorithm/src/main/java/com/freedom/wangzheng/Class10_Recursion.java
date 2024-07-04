package com.freedom.wangzheng;

/**
 * 假如这里有n个台阶，每次你可以跨1个台阶或者2个台阶，请问走这n个台阶有多少种走法？
 *
 * @author tobebetter9527
 * @create 2022/06/11 11:04
 */
public class Class10_Recursion {

    /**
     * @param n
     * @return
     */
    public static int recursion(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return recursion(n - 1) + recursion(n - 2);
    }

    public static void main(String[] args) {
        int n = 7;
        int recursion = recursion(n);
        System.out.println(recursion);
    }
}
