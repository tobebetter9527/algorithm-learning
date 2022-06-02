package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    int a = 6;
    int b = 6;
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println(a);
    System.out.println(b);
  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


}

