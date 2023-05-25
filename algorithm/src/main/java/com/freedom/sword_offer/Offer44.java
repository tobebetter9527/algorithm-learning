package com.freedom.sword_offer;

public class Offer44 {

  public static int findNthDigit(int n) {
    int digit = 1;
    long start = 1, count = 9;
    while (n > count) {
      n -= count;
      digit++;
      start *= 10;
      count = 9 * start * digit;
    }

    long num = start + (n - 1) / digit;
    int index = digit - 1 - ((n - 1) % digit);
    return (int) ((num / (int) Math.pow(10, index)) % 10);
  }

  public static void main(String[] args) {
    System.out.println(findNthDigit(1000000000));
  }
}
