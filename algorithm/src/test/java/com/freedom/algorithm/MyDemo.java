package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    Integer.valueOf("10111111111111111111111111111111", 2);

  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


}

