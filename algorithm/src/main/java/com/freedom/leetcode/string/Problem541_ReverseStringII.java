package com.freedom.leetcode.string;

/**
 * 541. Reverse String II
 *
 * @author tobebetter9527
 * @create 2022/10/23 8:55
 */
public class Problem541_ReverseStringII {

  /**
   *
   * @param s
   * @param k
   * @return
   */
  public static String reverseStr(String s, int k) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] chars = s.toCharArray();
    int n = chars.length;
    int doublek = k << 1;
    int loop = n / doublek;
    int remainder = n % doublek;

    for (int i = 0; i < loop; i++) {
      reverse(chars, doublek * i, doublek * i + k - 1);
    }

    remainder = remainder <= k ? remainder : k;
    reverse(chars, doublek * loop, doublek * loop + remainder - 1);

    return String.valueOf(chars);
  }

  private static void reverse(char[] chars, int left, int right) {
    char temp;
    while (left < right) {
      temp = chars[left];
      chars[left] = chars[right];
      chars[right] = temp;
      left++;
      right--;
    }
  }


  public static String reverseStr2(String s, int k) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] chars = s.toCharArray();
    int n = chars.length;
    int doublek = k << 1;
    for (int i = 0; i < n; i += doublek) {
      reverse(chars, i, Math.min(i + k, n) - 1);
    }
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    String s = "abcdef";
    int k = 2;
    String s1 = reverseStr2(s, k);
    System.out.println(s1);
  }
}
