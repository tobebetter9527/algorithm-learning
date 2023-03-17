package com.freedom.sword_offer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Offer58I {

  public static void main(String[] args) {
    String s = "   I am a    man    ";
    System.out.println(reverseWords(s));
  }

  public static String reverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    s = s.trim();
    // 单词的开头和结尾索引位置
    int left = s.length() - 1, right = left;
    // 从右往左匹配
    while (left >= 0) {
      // 找到第一个空格
      while (left >= 0 && s.charAt(left) != ' ') {
        left--;
      }
      // 添加单词
      sb.append(s, left + 1, right + 1).append(" ");
      // 找到后一个单词的末尾
      while (left >= 0 && s.charAt(left) == ' ') {
        left--;
      }
      // 后一个单词的末尾索引
      right = left;
    }
    return sb.toString().trim();
  }

  /**
   * 语言特性
   *
   * @param s
   * @return
   */
  public static String reverseWords2(String s) {
    s = s.trim();
    // 至少匹配一个空格
    String[] split = s.split("\\s+");
    List<String> strings = Arrays.asList(split);
    Collections.reverse(strings);
    return String.join(" ", strings);
  }

}
