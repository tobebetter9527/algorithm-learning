package com.freedom.zuo.class02_bit_manipulation;

public class Code01_Swap {

    public static void main(String[] args) {
        int a = 16;
        int b = 603;
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a);
        System.out.println("b=" + b);

        int[] arr = {3, 1, 100};
        System.out.println(arr[0]);
        swap(arr, 0, 0);
        System.out.println(arr[0]);


    }

    /**
     * 有bug，如果i == j的时候，出问题
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
