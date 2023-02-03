package com.freedom.sword_offer;

public class Offer14 {

  public static int cuttingRope(int n) {
    if (n <= 3) {
      return n - 1;
    }
    int quotient = n / 3, remainder = n % 3;
    if (remainder == 0) {
      return (int) Math.pow(3, quotient);
    } else if (remainder == 1) {
      return (int) (Math.pow(3,quotient -1) * 4);
    } else {
      return (int) (Math.pow(3, quotient) * 2);
    }
  }

  public static void main(String[] args) {
    int n = 10;
    int i = cuttingRope(n);
    System.out.println(i);
  }

}
