package com.freedom.sword_offer;

public class Offer46 {

  public static int translateNum(int num) {
    return recursive(num);
  }

  private static int recursive(int num) {
    // 当num=0时，找到一种转换方法
    if (num == 0) {
      return 1;
    }
    // 往左移动一位
    int p1 = recursive(num / 10);
    int p2 = 0;
    int temp = num % 100;
    // 往左移动两位，必须满足数字大于10即可以往左移动两位，同时分解出到temp在10到25
    if (num >= 10 && temp >= 10 && temp <= 25) {
      p2 = recursive(num / 100);
    }
    return p1 + p2;
  }

  public int translateNum2(int num) {
    int a = 1, b = 1;
    int x, y = num % 10;
    while (num != 0) {
      num /= 10;
      x = num % 10;
      int temp = 10 * x + y;
      int c = temp >= 10 && temp <= 25 ? a + b : a;
      b = a;
      a = c;
      y = x;
    }
    return a;
  }

  public static void main(String[] args) {
    int num = Integer.MAX_VALUE;
    System.out.println(translateNum(num));
  }
}
