package com.freedom.algorithm;

import java.util.LinkedList;
import java.util.Stack;

public class MyDemo {

  public static void main(String[] args) {
  }


  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


}

