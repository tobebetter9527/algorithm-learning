package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    int a = 200000000;
    System.out.println(a & (-a));

  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }
}

