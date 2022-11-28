package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    int i = pivotInteger(1);
    System.out.println(i);
  }

  public static int pivotInteger(int n) {
    if (n == 1) {
      return 1;
    }

    int[] sums = new int[n + 1];
    sums[0] = 0;
    for (int i = 1; i <= n; i++) {
      sums[i] = sums[i -1] + i;
    }

    for (int i = n -1; i >= 0; i--) {
      if (sums[n] - sums[i] == sums[i + 1]) {
        return i + 1;
      }
    }
    return -1;
  }


}

