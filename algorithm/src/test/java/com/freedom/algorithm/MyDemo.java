package com.freedom.algorithm;

import java.util.LinkedList;

public class MyDemo {

  public static void main(String[] args) {
    int a = 3;
    double b = 3.5;
    System.out.println(a > b);
  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


}

