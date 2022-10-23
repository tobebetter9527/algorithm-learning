package com.freedom.leetcode.string;

/**
 * 剑指 Offer 05. 替换空格
 *
 * @author tobebetter9527
 * @create 2022/10/23 9:38
 */
public class Problem_Offer05 {

  /**
   * time complexity is O(n),space complexity is O(1)
   *
   * @param s
   * @return
   */
  public String replaceSpace(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] chars = s.toCharArray();
    StringBuilder builder = new StringBuilder(s.length());
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == ' ') {
        builder.append("%20");
      } else {
        builder.append(chars[i]);
      }
    }

    return builder.toString();
  }


  public static String replaceSpace2(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] arr = new char[3 * s.length()];
    int size = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        arr[size++] = '%';
        arr[size++] = '2';
        arr[size++] = '0';
      } else {
        arr[size++]= s.charAt(i);
      }
    }

    return String.valueOf(arr,0,size);
  }

  public static void main(String[] args) {
    String s = "We are happy.";
    String s1 = replaceSpace2(s);
    System.out.println(s1);
  }

}
