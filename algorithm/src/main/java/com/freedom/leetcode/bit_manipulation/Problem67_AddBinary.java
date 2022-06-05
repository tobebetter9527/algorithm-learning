package com.freedom.leetcode.bit_manipulation;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * @author tobebetter9527
 * @create 2022/06/03 10:43
 */
public class Problem67_AddBinary {

  /**
   * 官方代码，思路跟我的一样，代码更简洁
   * @param a
   * @param b
   * @return
   */
  public static String addBinary2(String a, String b) {
    int aLength = a.length();
    int bLength = b.length();
    int max = Math.max(aLength, bLength);
    // 进位
    int carry = 0;

    StringBuilder sb = new StringBuilder(max + 1);
    for (int i = 0; i < max; i++) {
      carry += i < aLength ? a.charAt(aLength - 1 - i) - '0' : 0;
      carry += i < bLength ? b.charAt(bLength - 1 - i) - '0' : 0;
      sb.append(carry % 2);
      carry /= 2;
    }
    if (carry > 0) {
      sb.append("1");
    }
    return sb.reverse().toString();
  }

  public static String addBinary(String a, String b) {
    int max = Math.max(a.length(), b.length());
    int[] arr = new int[max];

    int aLength = a.length();
    int bLength = b.length();
    for (int i = 0; i < aLength; i++) {
      arr[aLength - 1 - i] += a.charAt(i) - '0';
    }
    for (int i = 0; i < bLength; i++) {
      arr[bLength - 1 - i] += b.charAt(i) - '0';
    }

    StringBuilder sb = new StringBuilder(max + 1);
    for (int i = 0; i < max; i++) {
      int num = arr[i];
      if (num < 2) {
        sb.append(num);
      } else {
        sb.append(num % 2);
        if (i + 1 < max) {
          arr[i + 1]++;
        } else {
          sb.append(num >= 2 ? "1" : "");
        }
      }
    }

    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    String a = "1010";
    String b = "1011";

    System.out.println(addBinary(a, b));
  }

}
