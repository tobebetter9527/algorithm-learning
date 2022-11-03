package com.freedom.leetcode.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets. Open brackets must be closed in the correct order.
 *
 * @author tobebetter9527
 * @create 2022/06/10 20:07
 */
public class Problem20_ValidParentheses {

  /**
   * 官方写法，时间复杂度：O(n)O(n)，空间复杂度：O(n + |\Sigma|)O(n+∣Σ∣)
   *
   * @param s
   * @return
   */
  public static boolean isValid1(String s) {
    int length = s.length();
    if (length % 2 == 1) {
      return false;
    }

    Map<Character, Character> map = new HashMap<Character, Character>() {{
      put(']', '[');
      put(')', '(');
      put('}', '{');
    }};

    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < length; i++) {
      char ch = s.charAt(i);
      if (map.containsKey(ch)) {
        if (stack.isEmpty() || !stack.peek().equals(map.get(ch))) {
          return false;
        }
        stack.pop();
      } else {
        stack.push(ch);
      }
    }
    return stack.isEmpty();
  }

  /**
   * 11月2号写法
   *
   * @param s
   * @return
   */
  public static boolean isValid2(String s) {
    if (s == null || (s.length() & 1) == 1) {
      return false;
    }

    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put(']', '[');
    map.put('}', '{');

    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      } else {
        if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  public static boolean isValid(String s) {
    int length = s.length();
    if (length % 2 == 1) {
      return false;
    }

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < length; i++) {
      char aChar = s.charAt(i);
      if ('(' == aChar || '{' == aChar || '[' == aChar) {
        stack.push(aChar);
      } else if (')' == aChar) {
        if (process(stack, '(')) {
          return false;
        }
      } else if ('}' == aChar) {
        if (process(stack, '{')) {
          return false;
        }
      } else if (']' == aChar) {
        if (process(stack, '[')) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  private static boolean process(Stack<Character> stack, char c) {
    if (!stack.isEmpty()) {
      Character pop = stack.pop();
      if (!pop.equals(c)) {
        return true;
      }
    } else {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    String s = "]";

    System.out.println(isValid2(s));
  }

}
