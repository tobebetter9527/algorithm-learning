package com.freedom.leetcode.string;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 *
 * @author tobebetter9527
 * @create 2022/10/23 16:48
 */
public class Problem_Offer58II {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param s
   * @param n
   */
  public static String reverseLeftWords(String s, int n) {
    if (s == null || s.length() < 2) {
      return s;
    }

    char[] oldArr = s.toCharArray();
    char[] newArr = new char[oldArr.length];
    int index = 0;
    for (int i = n; i < newArr.length; i++) {
      newArr[index++] = oldArr[i];
    }
    for (int i = 0; i < n; i++) {
      newArr[index++] = oldArr[i];
    }
    return String.valueOf(newArr);
  }

  /**
   * 要求不能额外申请空间
   * <p>
   * 先局部反转，后整体反转
   * <p>
   * time complexity is O(n), space complexity is O(1).
   *
   * @param s
   * @param n
   */
  public static String reverseLeftWords2(String s, int n) {
    if (s == null || s.length() < 2) {
      return s;
    }

    char[] arr = s.toCharArray();
    // 先局部反转0 ~ n-1，n ~ arr.length - 1
    reverse(arr, 0, n - 1);
    reverse(arr, n, arr.length - 1);

    // 整体反转
    reverse(arr, 0, arr.length - 1);
    return String.valueOf(arr);
  }

  private static void reverse(char[] arr, int left, int right) {
    char temp;
    while (left < right) {
      temp = arr[left];
      arr[left++] = arr[right];
      arr[right--] = temp;
    }
  }

  public static void main(String[] args) {
    String s = "lrloseumgh";
    int k = 6;
    System.out.println(reverseLeftWords2(s, k));
  }

}
