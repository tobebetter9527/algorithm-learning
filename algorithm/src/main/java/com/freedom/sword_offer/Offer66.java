package com.freedom.sword_offer;

public class Offer66 {

  /**
   * time complexity is O(n), space complexity is O(1).
   * @param a
   * @return
   */
  public static int[] constructArr(int[] a) {
    if (a == null || a.length == 0) {
      return new int[0];
    }
    int n = a.length, temp = 1;
    int[] b = new int[n];
    b[0] = 1;
    for (int i = 1; i < n; i++) {
      b[i] = b[i - 1] * a[i - 1];
    }
    for (int i = n -2; i >= 0; i--) {
      temp = temp * a[i + 1];
      b[i] = b[i] * temp;
    }
    return b;
  }

  public static void main(String[] args) {
    int[] a = {5,2};
    int[] b = constructArr(a);
    for (int i : b) {
      System.out.print(i + " ");
    }
  }

}
