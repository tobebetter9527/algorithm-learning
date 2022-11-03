package com.freedom.leetcode.stack;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String
 *
 * @author tobebetter9527
 * @create 2022/11/03 20:23
 */
public class Problem1047_RemoveAllAdjacentDuplicatesInString {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param s
   * @return
   */
  public static String removeDuplicates(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    Stack<Character> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      char c = chars[i];
      if (!stack.isEmpty() && stack.peek() == c) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    StringBuilder sb = new StringBuilder(stack.size());
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.toString();
  }

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param s
   * @return
   */
  public static String removeDuplicates2(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    char[] str = new char[n];
    int index = -1;
    for (int i = 0; i < n; i++) {
      char c = chars[i];
      if (index != -1 && str[index] == c) {
        index--;
      } else {
        str[++index] = c;
      }
    }

    return String.valueOf(str, 0, index + 1);
  }

  /**
   * 进一步优化的结果
   *
   * @param s
   * @return
   */
  public static String removeDuplicates3(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    int index = -1;
    for (int i = 0; i < n; i++) {
      char c = chars[i];
      if (index != -1 && chars[index] == c) {
        index--;
      } else {
        chars[++index] = c;
      }
    }

    return String.valueOf(chars, 0, index + 1);
  }

  public static void main(String[] args) {
    String aback = removeDuplicates2("azxxzy");
    System.out.println(aback);
  }

}
