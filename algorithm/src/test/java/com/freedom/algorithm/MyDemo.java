package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    int a = 6 << 0;
    int b = 6;

    System.out.println(a);
    System.out.println(b);

    Double c = 3124.2D;
    byte b1 = c.byteValue();
    System.out.println(b1);

  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


}

